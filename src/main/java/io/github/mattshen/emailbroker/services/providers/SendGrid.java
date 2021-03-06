package io.github.mattshen.emailbroker.services.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mattshen.emailbroker.Configuration;
import io.github.mattshen.emailbroker.Constants;
import io.github.mattshen.emailbroker.Utils;
import io.github.mattshen.emailbroker.models.Email;
import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component(Constants.EMAIL_PROVIDER_SENDGRID)
public class SendGrid implements EmailDeliveryProvider {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient httpClient;
    private Configuration conf;

    public SendGrid(Configuration conf) {
        this.httpClient = Utils.createHttpClient();
        this.conf = conf;
    }

    @Override
    public String name() {
        return Constants.EMAIL_PROVIDER_SENDGRID;
    }

    @Override
    public ProviderResponse send(SimpleEmailRequest request) {

        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String data = mapper.writeValueAsString(buildSendGridRequest(request));

            RequestBody jsonBody = RequestBody.create(JSON, data);
            Request req = new Request.Builder()
                    .header("Authorization", "Bearer " + conf.getSendgridApiKey())
                    .url(conf.getSendgridApiUrl())
                    .post(jsonBody)
                    .build();

            try (Response response = httpClient.newCall(req).execute()) {
                if (!response.isSuccessful()) {
                    log.warn(response.body().string());
                }
                return new ProviderResponse(
                        response.isSuccessful(),
                        response.code(),
                        response.header("X-Message-Id"),
                        name());
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            ProviderResponse res = new ProviderResponse(false, HttpStatus.BAD_REQUEST.value(), "", name());
            res.setMessage("Failed to call email delivery provider");
            return res;
        }
    }

    private SendGridRequest buildSendGridRequest(SimpleEmailRequest request) {
        SendGridRequest sgReq = new SendGridRequest();
        sgReq.setFrom(request.getFrom());
        sgReq.setSubject(request.getSubject());
        sgReq.setPersonalizations(Arrays.asList(new Recipients(request.getTo(), request.getCc(), request.getBcc())));
        sgReq.setContent(Arrays.asList(new Content(request.getContent())));
        return sgReq;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class SendGridRequest {

        public List<Content> content;
        private Email from;
        private String subject;
        private List<Recipients> personalizations;

    }

    @AllArgsConstructor
    @Data
    private static class Content {
        private String type = "text/plain";
        private String value;

        public Content(String value) {
            this.value = value;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Recipients {
        private List<Email> to;
        private List<Email> cc;
        private List<Email> bcc;
    }
}
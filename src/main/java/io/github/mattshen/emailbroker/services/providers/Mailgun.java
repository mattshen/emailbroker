package io.github.mattshen.emailbroker.services.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mattshen.emailbroker.Configuration;
import io.github.mattshen.emailbroker.Constants;
import io.github.mattshen.emailbroker.Utils;
import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component(Constants.EMAIL_PROVIDER_MAILGUN)
public class Mailgun implements EmailDeliveryProvider {

    private OkHttpClient httpClient;

    private Configuration conf;

    public Mailgun(Configuration conf) {
        this.httpClient = Utils.createHttpClient();
        this.conf = conf;
    }

    @Override
    public String name() {
        return Constants.EMAIL_PROVIDER_MAILGUN;
    }

    @Override
    public ProviderResponse send(SimpleEmailRequest request) {

        String creds = Credentials.basic(conf.getMailgunApiUser(), conf.getMailgunApiPassword());
        Request req = new Request.Builder()
                .header("Authorization", creds)
                .url(conf.getMailgunApiUrl())
                .post(buildRequestBody(request))
                .build();

        try (Response response = httpClient.newCall(req).execute()) {
            //extract mailgun id
            String id = "";
            if (response.isSuccessful()) {
                Map<String, Object> responseBody = new ObjectMapper().readerFor(HashMap.class)
                        .readValue(response.body().string());
                id = String.valueOf(responseBody.get("id"));
            }

            if (!response.isSuccessful()) {
                log.warn(response.body().string());
            }

            return new ProviderResponse(
                    response.isSuccessful(),
                    response.code(),
                    id,
                    name());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            ProviderResponse res = new ProviderResponse(false, HttpStatus.BAD_REQUEST.value(), "", name());
            res.setMessage("Failed to call email delivery provider");
            return res;
        }

    }

    private RequestBody buildRequestBody(SimpleEmailRequest request) {

        FormBody.Builder builder = new FormBody.Builder()
                .add("from", request.getFrom().toString())
                .add("to", Utils.stringifyRecipients(request.getTo()))
                .add("subject", request.getSubject())
                .add("text", request.getContent());

        if (request.getCc() != null) {
            builder.add("cc", Utils.stringifyRecipients(request.getCc()));
        }
        if (request.getBcc() != null) {
            builder.add("bcc", Utils.stringifyRecipients(request.getBcc()));
        }

        return builder.build();

    }

}



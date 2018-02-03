package io.github.mattshen.emailbroker.services.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mattshen.emailbroker.Configuration;
import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;
import io.github.mattshen.emailbroker.messages.SendGridRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("sendgrid")
public class SendGrid implements EmailDeliveryProvider {

    private OkHttpClient httpClient;

    private Configuration conf;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public SendGrid(Configuration conf) {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.conf = conf;
    }

    @Override
    public EmailDeliveryResponse send(EmailRequest request) {

        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);;

        SendGridRequest requestV2 = new SendGridRequest(
                request.getTo(),
                request.getFrom(),
                request.getSubject(),
                request.getText()
        );

        try {
            String data = mapper.writeValueAsString(requestV2);

            RequestBody jsonBody = RequestBody.create(JSON, data);
            Request req = new Request.Builder()
                    .header("Authorization", "Bearer " + conf.getSendgridApiKey())
                    .url(conf.getSendgridApiUrl())
                    .post(jsonBody)
                    .build();

            try (Response response = httpClient.newCall(req).execute()) {
                return new EmailDeliveryResponse("test", response.isSuccessful(), response.body().string());
            } catch (IOException e) {
                throw e;
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new EmailDeliveryResponse(true, e.getMessage());
        }

    }
}
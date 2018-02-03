package io.github.mattshen.emailbroker.services.providers;

import io.github.mattshen.emailbroker.Configuration;
import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("mailgun")
public class Mailgun implements EmailDeliveryProvider {

    private OkHttpClient httpClient;

    private Configuration conf;

    public Mailgun(Configuration conf) {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.conf = conf;
    }

    @Override
    public EmailDeliveryResponse send(EmailRequest request) {

        RequestBody formBody = new FormBody.Builder()
                .add("from", request.getFrom())
                .add("to", request.getTo())
                .add("subject", request.getSubject())
                .add("text", request.getText())
                .build();

        String creds = Credentials.basic(conf.getMailgunApiUser(), conf.getMailgunApiPassword());
        Request req = new Request.Builder()
                .header("Authorization", creds)
                .url(conf.getMailgunApiUrl())
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(req).execute()) {
            return new EmailDeliveryResponse("test", response.isSuccessful(), response.body().string());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new EmailDeliveryResponse(false, e.getMessage());
        }

    }

}



package io.github.mattshen.emailbroker.services.providers;

import io.github.mattshen.emailbroker.Configuration;
import io.github.mattshen.emailbroker.Utils;
import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component("mailgun")
public class Mailgun implements EmailDeliveryProvider {

    private OkHttpClient httpClient;

    private Configuration conf;

    public Mailgun(Configuration conf) {
        this.httpClient = Utils.createHttpClient();
        this.conf = conf;
    }

    @Override
    public EmailDeliveryResponse send(SimpleEmailRequest request) {

        String creds = Credentials.basic(conf.getMailgunApiUser(), conf.getMailgunApiPassword());
        Request req = new Request.Builder()
                .header("Authorization", creds)
                .url(conf.getMailgunApiUrl())
                .post(buildRequestBody(request))
                .build();

        try (Response response = httpClient.newCall(req).execute()) {
            return new EmailDeliveryResponse("test", response.isSuccessful(), response.body().string());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new EmailDeliveryResponse(false, e.getMessage());
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



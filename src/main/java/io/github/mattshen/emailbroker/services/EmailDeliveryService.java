package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.Constants;
import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailDeliveryService {

    private EmailDeliveryProvider primaryProvider;
    private EmailDeliveryProvider secondaryProvider;

    public EmailDeliveryService(@Qualifier(Constants.EMAIL_PROVIDER_SENDGRID) EmailDeliveryProvider first,
                                @Qualifier(Constants.EMAIL_PROVIDER_MAILGUN) EmailDeliveryProvider second) {

        this.primaryProvider = first;
        this.secondaryProvider = second;
    }

    public EmailDeliveryResponse send(SimpleEmailRequest request) {
        EmailDeliveryResponse response = primaryProvider.send(request);
        if (response.isSuccess()) {
            return response;
        } else {
            log.error("Primary EmailDeliveryProvider encounter errors");
            return secondaryProvider.send(request);
        }
    }

}

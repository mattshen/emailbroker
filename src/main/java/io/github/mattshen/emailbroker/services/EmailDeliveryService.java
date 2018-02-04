package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailDeliveryService {

    private EmailDeliveryProvider primaryProvider;

    private EmailDeliveryProvider secondaryProvider;

    public EmailDeliveryService(@Qualifier("sendgrid") EmailDeliveryProvider first,
                                @Qualifier("mailgun") EmailDeliveryProvider second) {

        this.primaryProvider = first;
        this.secondaryProvider = second;
    }

    public EmailDeliveryResponse send(SimpleEmailRequest request) {
        EmailDeliveryResponse response = primaryProvider.send(request);
        if (response.isSuccess()) {
            return response;
        } else {
            return secondaryProvider.send(request);
        }
    }

}

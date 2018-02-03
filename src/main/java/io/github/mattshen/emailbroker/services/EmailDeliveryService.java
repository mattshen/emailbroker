package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailDeliveryService {

    @Autowired
    @Qualifier("mailgun")
    private EmailDeliveryProvider primaryProvider;

    @Autowired
    @Qualifier("sendgrid")
    private EmailDeliveryProvider secondaryProvider;


    public EmailDeliveryResponse send(EmailRequest request) {
        return primaryProvider.send(request);
    }

}

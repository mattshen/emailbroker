package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.Constants;
import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailDeliveryService {

    private EmailDeliveryProvider primaryProvider;
    private EmailDeliveryProvider secondaryProvider;

    public EmailDeliveryService(@Qualifier(Constants.EMAIL_PROVIDER_MAILGUN) EmailDeliveryProvider first,
                                @Qualifier(Constants.EMAIL_PROVIDER_SENDGRID) EmailDeliveryProvider second) {

        this.primaryProvider = first;
        this.secondaryProvider = second;
    }

    public ProviderResponse send(SimpleEmailRequest request) {
        ProviderResponse response1 = primaryProvider.send(request);
        if (response1.isSuccess()) {
            return response1;
        } else {
            log.error("Primary EmailDeliveryProvider encounter errors");
            ProviderResponse response2 = secondaryProvider.send(request);
            return response2;
        }
    }

}

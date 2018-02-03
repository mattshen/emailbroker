package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;

public interface EmailDeliveryProvider {

    EmailDeliveryResponse send(EmailRequest request);

}

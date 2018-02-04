package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;

public interface EmailDeliveryProvider {
    EmailDeliveryResponse send(SimpleEmailRequest request);
}

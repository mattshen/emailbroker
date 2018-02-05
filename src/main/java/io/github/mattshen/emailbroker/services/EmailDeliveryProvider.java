package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;

public interface EmailDeliveryProvider {
    String name();
    ProviderResponse send(SimpleEmailRequest request);
}

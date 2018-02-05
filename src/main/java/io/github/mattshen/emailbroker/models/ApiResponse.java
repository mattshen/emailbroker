package io.github.mattshen.emailbroker.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Setter(value = AccessLevel.NONE)
public class ApiResponse {

    private boolean success;
    private ProviderResponse deliveryResult;

    public ApiResponse(boolean success, ProviderResponse deliveryResult) {
        this.success = success;
        this.deliveryResult = deliveryResult;
    }

}

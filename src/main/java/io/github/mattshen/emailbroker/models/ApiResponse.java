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
    private EmailDeliveryResponse deliveryResult;

    public ApiResponse(boolean success, EmailDeliveryResponse deliveryResult) {
        this.success = success;
        this.deliveryResult = deliveryResult;
    }

}

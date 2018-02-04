package io.github.mattshen.emailbroker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailDeliveryResponse {

    private String id;
    private int httpCode;
    private boolean success;

    public EmailDeliveryResponse(boolean success, int httpCode, String id) {
        this.success = success;
        this.httpCode = httpCode;
        this.id = id;
    }

}

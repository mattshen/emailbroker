package io.github.mattshen.emailbroker.messages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
@Setter(value = AccessLevel.NONE)
public class EmailDeliveryResponse {

    private String id;
    private boolean success;
    private String message;

    public EmailDeliveryResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

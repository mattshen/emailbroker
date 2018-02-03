package io.github.mattshen.emailbroker.messages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Data
@Setter(value = AccessLevel.NONE)
public class ApiResponse {

    private boolean success = false;
    private String message;
    private Map<String, Object> data;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}

package io.github.mattshen.emailbroker.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderResponse {

    private String id;
    private int httpCode;
    private boolean success;
    private String providerName;
    private String message;

    public ProviderResponse(boolean success, int httpCode, String id, String providerName) {
        this.success = success;
        this.httpCode = httpCode;
        this.id = id;
        this.providerName = providerName;
    }

}

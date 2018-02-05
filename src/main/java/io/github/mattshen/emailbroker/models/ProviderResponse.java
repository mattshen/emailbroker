package io.github.mattshen.emailbroker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderResponse {

    private String id;
    private int httpCode;
    private boolean success;
    private String providerName;

    public ProviderResponse(boolean success, int httpCode, String id, String providerName) {
        this.success = success;
        this.httpCode = httpCode;
        this.id = id;
        this.providerName = providerName;
    }

}

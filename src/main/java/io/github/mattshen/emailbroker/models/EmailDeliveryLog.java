package io.github.mattshen.emailbroker.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDeliveryLog {
    @Id
    private String id;

    private SimpleEmailRequest request;

    private ProviderResponse response;

    public EmailDeliveryLog(SimpleEmailRequest request) {
        this.request = request;
    }

    public EmailDeliveryLog(SimpleEmailRequest request, ProviderResponse response) {
        this.request = request;
        this.response = response;
    }
}

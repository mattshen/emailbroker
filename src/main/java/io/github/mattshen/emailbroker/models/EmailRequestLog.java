package io.github.mattshen.emailbroker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestLog {
    @Id
    private String id;

    private SimpleEmailRequest request;

    private ProviderResponse response;

    public EmailRequestLog(SimpleEmailRequest request) {
        this.request = request;
    }

    public EmailRequestLog(SimpleEmailRequest request, ProviderResponse response) {
        this.request = request;
        this.response = response;
    }
}

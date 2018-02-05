package io.github.mattshen.emailbroker.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleEmailRequest {

    @NotNull
    @Valid
    private Email from;

    @NotEmpty
    private String subject;

    @NotEmpty
    @Valid
    private List<Email> to;

    @Valid
    private List<Email> cc;

    @Valid
    private List<Email> bcc;

    @NotEmpty
    public String content;

    public SimpleEmailRequest(Email from, List<Email> to, String subject, String content) {
        this.from = from;
        this.subject = subject;
        this.to = to;
        this.content = content;
    }

}

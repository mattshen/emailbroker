package io.github.mattshen.emailbroker.messages;

import io.github.mattshen.emailbroker.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendGridRequest {

    private Email from;
    private String subject;
    private List<Personalization> personalizations;
    public List<Content> content;

    public SendGridRequest(String to, String from, String subject, String text) {
        this.from = new Email(from);
        this.subject = subject;
        this.content = Arrays.asList(new Content(text));
        this.personalizations = Arrays.asList(new Personalization(to));
    }

}

@AllArgsConstructor
@Data
class Content {
    private String type = Constants.DEFAULT_CONTENT_TYPE;
    private String value;

    public Content(String value) {
        this.value = value;
    }
}

@AllArgsConstructor
@Data
class Personalization {
    private List<Email> to;
    private List<Email> cc;
    private List<Email> bcc;

    public Personalization(String to) {
        this.to = Arrays.asList(new Email(to));
    }
}


@AllArgsConstructor
@Data
class Email {
    private String name;
    private String email;

    public Email(String email) {
        this.email = email;
    }
}

package io.github.mattshen.emailbroker.messages;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailRequest {

    private String to;
    private String from;
    private String replyTo;
    private String cc;
    private String bcc;
    private String subject;
    private String text;

}

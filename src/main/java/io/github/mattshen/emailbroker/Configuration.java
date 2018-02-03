package io.github.mattshen.emailbroker;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Configuration {

    @Value("${SENDGRID_API_URL}")
    private String sendgridApiUrl;

    @Value("${SENDGRID_API_KEY}")
    private String sendgridApiKey;

    @Value("${MAILGUN_API_URL}")
    private String mailgunApiUrl;

    @Value("${MAILGUN_API_USER}")
    private String mailgunApiUser;

    @Value("${MAILGUN_API_PASSWORD}")
    private String mailgunApiPassword;

}

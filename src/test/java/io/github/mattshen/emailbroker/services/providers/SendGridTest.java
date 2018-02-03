package io.github.mattshen.emailbroker.services.providers;

import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SendGridTest {

    private SendGrid sendGrid;

    @Before
    public void setUp() {
        sendGrid = new SendGrid();
    }

    @Test
    public void send() {
        EmailRequest request = new EmailRequest();
        request.setFrom("lovelywib@gmail.com");
        request.setTo("lovelywib@gmail.com");
        request.setSubject("testing mailgun");
        request.setText("It works!!!");

        EmailDeliveryResponse res = sendGrid.send(request);
        System.out.println(res.getMessage());
    }
}
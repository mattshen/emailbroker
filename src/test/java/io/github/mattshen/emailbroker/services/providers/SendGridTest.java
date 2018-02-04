package io.github.mattshen.emailbroker.services.providers;

import io.github.mattshen.emailbroker.TestUtils;
import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendGridTest {

    @Autowired
    private SendGrid sendGrid;

    @Test
    public void send_should_work() {
        EmailDeliveryResponse res = sendGrid.send(TestUtils.createSimpleEmailRequest());
        Assert.assertTrue(res.isSuccess());
    }
}
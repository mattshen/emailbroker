package io.github.mattshen.emailbroker.utils;

import io.github.mattshen.emailbroker.Utils;
import io.github.mattshen.emailbroker.models.Email;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UtilsTest {

    @Test
    public void stringifyRecipients() {

        Email email1 = new Email("Matt 1", "lovelywib@gmail.com");
        Email email2 = new Email("Matt 2", "lovelywib+2@gmail.com");

        Assert.assertEquals(
                "Matt 1 <lovelywib@gmail.com>,Matt 2 <lovelywib+2@gmail.com>",
                Utils.stringifyRecipients(Arrays.asList(email1, email2)));

        Assert.assertNull(Utils.stringifyRecipients(null));

    }
}
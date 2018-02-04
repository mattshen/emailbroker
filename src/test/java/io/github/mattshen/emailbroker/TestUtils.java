package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.models.Email;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;

import java.util.Arrays;

public class TestUtils {

    public static SimpleEmailRequest createSimpleEmailRequest() {

        Email from = new Email("User A", "lovelywib@gmail.com");
        Email to = new Email("User B", "lovelywib+test@gmail.com");

        SimpleEmailRequest request = new SimpleEmailRequest(
                from,
                Arrays.asList(to),
                "A email from user A to user B",
                "It works!"
        );

        return request;
    }

}

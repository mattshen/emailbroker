package io.github.mattshen.emailbroker;


import io.github.mattshen.emailbroker.models.Email;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String stringifyRecipients(List<Email> emails) {
        if (emails == null) {
            return null;
        }
        List<String> recipientStrings = new ArrayList<>();
        emails.forEach(o -> recipientStrings.add(o.toString()));
        return String.join(",", recipientStrings);
    }

    public static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

}

package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.services.EmailDeliveryProvider;
import io.github.mattshen.emailbroker.services.providers.SendGrid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


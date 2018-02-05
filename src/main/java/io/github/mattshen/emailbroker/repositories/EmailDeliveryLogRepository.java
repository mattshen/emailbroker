package io.github.mattshen.emailbroker.repositories;

import io.github.mattshen.emailbroker.models.EmailDeliveryLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailDeliveryLogRepository extends MongoRepository<EmailDeliveryLog, String> {
}

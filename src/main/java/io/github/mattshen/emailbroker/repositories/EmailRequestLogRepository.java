package io.github.mattshen.emailbroker.repositories;

import io.github.mattshen.emailbroker.models.EmailRequestLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRequestLogRepository extends MongoRepository<EmailRequestLog, String> {
}

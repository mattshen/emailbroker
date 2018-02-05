package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.EmailDeliveryLog;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.repositories.EmailDeliveryLogRepository;
import io.github.mattshen.emailbroker.services.EmailDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/emails")
@ControllerAdvice
public class Endpoints {

    private EmailDeliveryService service;

    @Autowired
    private EmailDeliveryLogRepository repository;

    public Endpoints(EmailDeliveryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<EmailDeliveryLog> send(@Valid @RequestBody SimpleEmailRequest request) {
        ProviderResponse providerResponse = service.send(request);
        EmailDeliveryLog deliveryLog = repository.save(new EmailDeliveryLog(request, providerResponse));
        return ok(deliveryLog);
    }

    @GetMapping
    public ResponseEntity<List<EmailDeliveryLog>> findAllEmailLogs() {
        return ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailDeliveryLog> findEmailLogById(@PathVariable("id") String id) {
        EmailDeliveryLog requestLog = repository.findOne(id);
        if (requestLog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ok(requestLog);
    }

}

package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.EmailRequestLog;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.models.ApiResponse;
import io.github.mattshen.emailbroker.repositories.EmailRequestLogRepository;
import io.github.mattshen.emailbroker.services.EmailDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/emails")
@ControllerAdvice
public class Endpoints {

    private EmailDeliveryService service;

    @Autowired
    private EmailRequestLogRepository repository;

    public Endpoints(EmailDeliveryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> send(@Valid @RequestBody SimpleEmailRequest request) {
        ProviderResponse providerResponse = service.send(request);
        repository.save(new EmailRequestLog(request, providerResponse));
        return ok(new ApiResponse(providerResponse.isSuccess(), providerResponse));
    }

    @GetMapping
    public ResponseEntity<List<EmailRequestLog>> findAllEmailLogs() {
        return ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailRequestLog> findEmailLogById(@PathVariable("id") String id) {
        EmailRequestLog requestLog = repository.findOne(id);
        if (requestLog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ok(requestLog);
    }

}

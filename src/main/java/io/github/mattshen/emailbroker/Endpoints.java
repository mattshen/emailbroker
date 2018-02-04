package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.models.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.models.ApiResponse;
import io.github.mattshen.emailbroker.services.EmailDeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/emails")
@ControllerAdvice
public class Endpoints {

    private EmailDeliveryService service;

    public Endpoints(EmailDeliveryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> send(@Valid @RequestBody SimpleEmailRequest request) {
        EmailDeliveryResponse deliveryResponse = service.send(request);
        return ResponseEntity.ok(new ApiResponse(deliveryResponse.isSuccess(), deliveryResponse.getMessage()));
    }

}

package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.messages.EmailDeliveryResponse;
import io.github.mattshen.emailbroker.messages.EmailRequest;
import io.github.mattshen.emailbroker.messages.ApiResponse;
import io.github.mattshen.emailbroker.services.EmailDeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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
    public ResponseEntity<ApiResponse> send(@RequestBody EmailRequest request) {
        EmailDeliveryResponse deliveryResponse = service.send(request);
        return ResponseEntity.ok(new ApiResponse(deliveryResponse.isSuccess(), deliveryResponse.getMessage()));
    }

}

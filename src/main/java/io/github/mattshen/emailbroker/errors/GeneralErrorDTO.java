package io.github.mattshen.emailbroker.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralErrorDTO {

    private String message;
    private List<FieldErrorDTO> fieldErrors;

    public GeneralErrorDTO(String message) {
        this.message = message;
    }

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        if (fieldErrors == null)
            fieldErrors = new ArrayList<>();
        fieldErrors.add(error);
    }

}
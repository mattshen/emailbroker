package io.github.mattshen.emailbroker.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorDTO {
 
    private String field;
    private String message;

}
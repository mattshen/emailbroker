package io.github.mattshen.emailbroker.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Email {

    private String name;

    @NotEmpty
    @org.hibernate.validator.constraints.Email
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String toString(){
        return StringUtils.hasLength(name) ? name + " <" + email + ">" : email;
    }

}

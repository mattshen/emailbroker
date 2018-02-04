package io.github.mattshen.emailbroker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {

    private String name;

    @NotEmpty
    @org.hibernate.validator.constraints.Email
    private String email;

    public String toString(){
        return StringUtils.hasLength(name) ? name + " <" + email + ">" : email;
    }

}

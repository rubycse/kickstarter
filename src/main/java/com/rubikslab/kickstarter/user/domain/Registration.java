package com.rubikslab.kickstarter.user.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;

/**
 * @author lutfun
 * @since 10/29/18
 */
@Data
public class Registration {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String confirmPassword;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotNull
    private Gender gender;

    @NotEmpty
    private String email;

    private String phone;

    public User toUser() {
        return new User(null, username, password, firstName, lastName,
                birthDate, gender, email, phone, Collections.emptyList());
    }
}

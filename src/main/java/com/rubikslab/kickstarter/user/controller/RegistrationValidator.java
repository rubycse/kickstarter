package com.rubikslab.kickstarter.user.controller;

import com.rubikslab.kickstarter.user.domain.Registration;
import com.rubikslab.kickstarter.user.service.UserService;
import com.rubikslab.kickstarter.common.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author lutfun
 * @since 10/29/18
 */
@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Registration user = (Registration) target;

        if (!Utils.isEmpty(user.getPassword()) && !Utils.isEmpty(user.getConfirmPassword()) &&
                !user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "error.confirmPassword.doesNotMatch");
        }

        if (!Utils.isEmpty(user.getUsername()) && userService.isUserExistsWithUsername(user.getUsername())) {
            errors.rejectValue("username", "auth.username.alreadyTaken");
        }

        if (!Utils.isEmpty(user.getEmail()) && userService.isUserExistsWithEmail(user.getEmail())) {
            errors.rejectValue("email", "auth.email.alreadyUsed");
        }
    }
}

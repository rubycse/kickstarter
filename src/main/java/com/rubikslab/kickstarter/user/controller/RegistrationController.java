package com.rubikslab.kickstarter.user.controller;

import com.rubikslab.kickstarter.user.domain.Gender;
import com.rubikslab.kickstarter.user.domain.Registration;
import com.rubikslab.kickstarter.user.domain.User;
import com.rubikslab.kickstarter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author lutfun
 * @since 10/29/18
 */
@Slf4j
@Controller
@SessionAttributes("registration")
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RegistrationValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(validator);
    }

    @GetMapping
    public String get(ModelMap model) {
        model.put("registration", new Registration());

        return "user/registration";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("registration") Registration registration, BindingResult errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().toString());
            return "user/registration";
        }

        User user = registration.toUser();
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/login";
    }

    @ModelAttribute("genders")
    public List<Gender> getGenders() {
        return Arrays.asList(Gender.values());
    }
}

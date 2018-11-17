package com.rubikslab.kickstarter.user.controller;

import com.rubikslab.kickstarter.RecordNotFound;
import com.rubikslab.kickstarter.user.domain.Gender;
import com.rubikslab.kickstarter.user.domain.Role;
import com.rubikslab.kickstarter.user.domain.User;
import com.rubikslab.kickstarter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author lutfun
 * @since 11/1/18
 */
@Controller
@SessionAttributes("profile")
@RequestMapping("/profiles")
@Slf4j
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @InitBinder("profile")
    public void initBinder(WebDataBinder binder, Authentication authentication) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        User profile = (User) binder.getTarget();
        if (profile != null) {
            binder.setAllowedFields(getAllowedFields(profile, (User) authentication.getPrincipal()));
        }
    }

    private String[] getAllowedFields(User profile, User currentUser) {
        List<String> allowedFields = new ArrayList<>();
        if (currentUser.getId().equals(profile.getId())) {
            allowedFields.addAll(Arrays.asList("firstName", "lastName", "birthDate", "gender", "email", "phone"));
        }
        if (currentUser.getRoles().contains(Role.ROLE_ADMIN)) {
            allowedFields.add("roles");
        }

        return allowedFields.toArray(new String[0]);
    }

    @GetMapping("/myProfile")
    public String get(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return "redirect:" + principal.getId();
    }

    @GetMapping(path = "/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == principal.id")
    public String getUser(@PathVariable("userId") Long userId, ModelMap model) {

        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new RecordNotFound("User not found!");
        }

        model.put("profile", user.get());

        return "user/profile";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or #user.id == principal.id")
    public String save(@Valid @ModelAttribute("profile") User user, BindingResult errors) {

        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());
            return "user/profile";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @ModelAttribute("genders")
    public List<Gender> getGenders() {
        return Arrays.asList(Gender.values());
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
}

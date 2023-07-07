package com.sayonara.Library_SpringBoot.controllers;

import com.sayonara.Library_SpringBoot.models.User;
import com.sayonara.Library_SpringBoot.services.UserService;
import com.sayonara.Library_SpringBoot.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String authenticated() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";

        userService.register(user);
        return "startPage";
    }
}

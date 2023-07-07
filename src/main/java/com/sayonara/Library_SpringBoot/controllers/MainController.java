package com.sayonara.Library_SpringBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // переписать определение аутентифицированного юзера
        if (!authentication.getPrincipal().toString().equals("anonymousUser")){
            model.addAttribute("user", authentication.getPrincipal());
        } else
            return "auth/login";
        return "startPage";
    }

}

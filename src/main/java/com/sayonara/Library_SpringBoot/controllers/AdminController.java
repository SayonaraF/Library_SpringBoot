package com.sayonara.Library_SpringBoot.controllers;

import com.sayonara.Library_SpringBoot.models.User;
import com.sayonara.Library_SpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/adminPage";
    }

    @PostMapping
    public String setRole(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.updateRole(user.getId());
        return "redirect:/admin";
    }
}

package com.vr.here.api.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "register"; // returns the register.jsp view
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, Model model) {
        // Add user registration logic here
        model.addAttribute("message", "Registration successful for user: " + username);
        return "index"; // return to the registration page with a success message
    }
}
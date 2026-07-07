package com.example.unsichere_web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login") 
    public String processLogin(
        @RequestParam String username, 
        @RequestParam String password, 
        Model model)
    {
        if (username.equals("admin") && password.equals("admin123")) { //TODO: Add database queries
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

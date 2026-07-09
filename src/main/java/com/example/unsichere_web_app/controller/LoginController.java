package com.example.unsichere_web_app.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.unsichere_web_app.models.User;

import com.example.unsichere_web_app.repositories.UserRepository;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
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
        User user = userRepository.findByUsernameAndPassword(username, password);
        
        if (user != null) {
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

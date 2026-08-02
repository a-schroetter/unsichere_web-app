package com.example.unsichere_web_app.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.unsichere_web_app.models.User;
import com.example.unsichere_web_app.repositories.UserRepository;

@Controller
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username, @RequestParam String password, Model model) {
        User existing = userRepository.findByUsername(username);
        if (existing != null) {
            model.addAttribute("error", "Username already taken");
            return "register";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepository.saveUser(new User(0, username, passwordEncoder.encode(password)));
        return "redirect:/login";
    }
}

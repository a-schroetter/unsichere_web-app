package com.example.unsichere_web_app.controller;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.unsichere_web_app.models.User;

import com.example.unsichere_web_app.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final HashMap<String, Integer> failedAttempts = new HashMap<>();
    private static final int MAX_ATTEMPTS = 5;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (failedAttempts.getOrDefault(username, 0) >= MAX_ATTEMPTS) {
            model.addAttribute("error", "Account blocked due to too many failed attempts");
            return "login";
        }

        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            failedAttempts.remove(username);
            session.setAttribute("sessionUser", user.getUsername());
            return "redirect:/profile";
        } else {
            int attempts = failedAttempts.getOrDefault(username, 0) + 1;
            failedAttempts.put(username, attempts);

            if (attempts > MAX_ATTEMPTS) {
                model.addAttribute("error", "account blocked");
            } else {
                model.addAttribute("error", "Invalid username or password");
            }
            return "login";
        }
    }
}

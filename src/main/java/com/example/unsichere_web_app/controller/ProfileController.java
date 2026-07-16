package com.example.unsichere_web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.unsichere_web_app.models.User;
import com.example.unsichere_web_app.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String showProfilePage(@RequestParam(required = false) Integer id,
            HttpSession session,
            Model model) {
        String sessionUsername = (String) session.getAttribute("sessionUser");
        if (sessionUsername == null)
            return "redirect:/login";

        User user;
        if (id == null) {
            user = userRepository.findByUsername(sessionUsername);
        } else {
            user = userRepository.findById(id);
            if (user == null || !sessionUsername.equals(user.getUsername())) {
                return "redirect:/profile";
            }
        }
        model.addAttribute("user", user);
        return "profile";
    }
}

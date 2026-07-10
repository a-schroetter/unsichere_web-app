package com.example.unsichere_web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("sessionUser");
        if (username == null) return "redirect:/login";
        model.addAttribute("username", username);
        return "profile";
    }
}

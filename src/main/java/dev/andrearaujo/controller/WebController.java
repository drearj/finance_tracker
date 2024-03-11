package dev.andrearaujo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/ui")
    public String showFinance(Model model) {
        model.addAttribute("message", "Bem-vindo à interface web!");

        return "finance";
    }
}
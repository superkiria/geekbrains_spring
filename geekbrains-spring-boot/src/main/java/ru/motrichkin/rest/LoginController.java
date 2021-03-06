package ru.motrichkin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.motrichkin.persistence.User;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String formUser(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}

package be.saxomoose.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {

    @GetMapping("/logout")
    public String logoutForm() {
        return "auth/logout";
    }
}

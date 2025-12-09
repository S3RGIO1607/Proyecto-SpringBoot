package com.arron.Arron.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        // Elimina todo lo de la sesión
        session.invalidate();

        // Redirige al login
        return "redirect:/iniciarsesion";
    }
}

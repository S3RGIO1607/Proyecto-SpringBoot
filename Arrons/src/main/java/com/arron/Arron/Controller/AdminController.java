package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/perfil")
    public String perfilAdmin(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Validación de sesión y tipo de usuario
        if (usuario == null || usuario.getTipoUsuario() != Usuario.TipoUsuario.Administrador) {
            return "redirect:/iniciarsesion";
        }

        // Agregar usuario al modelo (NECESARIO para que el menú aparezca)
        model.addAttribute("usuario", usuario);

        // Agregar título para el layout (Recomendado)
        model.addAttribute("title", "Perfil Administrador");

        return "admin/perfil";
    }
}


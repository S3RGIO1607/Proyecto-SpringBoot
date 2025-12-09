package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --------------------------
    // LISTAR (INDEX)
    // --------------------------
    @GetMapping
    public String listarUsuarios(Model model) {

        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "admin/usuarios/index";
    }

    // --------------------------
    // FORMULARIO CREAR
    // --------------------------
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuarios/crear";
    }

    // --------------------------
    // GUARDAR (POST)
    // --------------------------
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    // --------------------------
    // FORMULARIO EDITAR
    // --------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);

        return "admin/usuarios/editar";
    }

    // --------------------------
    // ACTUALIZAR (POST)
    // --------------------------
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.actualizarUsuario(usuario);
        return "redirect:/usuarios";
    }

    // --------------------------
    // ELIMINAR
    // --------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/consultar/{id}")
    public String consultarUsuario(@PathVariable Long id, Model model) {

        Usuario usuario = usuarioService.obtenerPorId(id);

        if (usuario == null) {
            return "redirect:/usuarios?error=no_existe";
        }

        model.addAttribute("usuario", usuario);

        return "admin/usuarios/consultar";
    }
}


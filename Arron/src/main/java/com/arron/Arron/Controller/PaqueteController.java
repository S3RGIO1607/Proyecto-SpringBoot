package com.arron.Arron.Controller;

import com.arron.Arron.Model.Paquete;
import com.arron.Arron.Service.PaqueteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    // ----------------------------------------
    // LISTAR
    // ----------------------------------------
    @GetMapping
    public String listarPaquetes(Model model) {
        model.addAttribute("paquetes", paqueteService.listarTodos());
        return "admin/Paquetes/index";
    }

    // ----------------------------------------
    // FORMULARIO CREAR
    // ----------------------------------------
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("paquete", new Paquete());
        return "admin/Paquetes/crear";
    }

    // ----------------------------------------
    // GUARDAR
    // ----------------------------------------
    @PostMapping("/guardar")
    public String guardarPaquete(@ModelAttribute Paquete paquete) {
        paqueteService.guardar(paquete);
        return "redirect:/paquetes";
    }

    // ----------------------------------------
    // EDITAR
    // ----------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("paquete", paqueteService.buscarPorId(id));
        return "admin/Paquetes/editar";
    }

    // ----------------------------------------
    // ACTUALIZAR
    // ----------------------------------------
    @PostMapping("/actualizar")
    public String actualizarPaquete(@ModelAttribute Paquete paquete) {
        paqueteService.guardar(paquete);
        return "redirect:/paquetes";
    }

    // ----------------------------------------
    // ELIMINAR
    // ----------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        paqueteService.eliminar(id);
        return "redirect:/paquetes";
    }

    // ----------------------------------------
    // CONSULTAR
    // ----------------------------------------
    @GetMapping("/consultar/{id}")
    public String consultarPaquete(@PathVariable Long id, Model model) {

        Paquete paquete = paqueteService.buscarPorId(id);

        if (paquete == null) {
            return "redirect:/paquetes?error=no_existe";
        }

        model.addAttribute("paquete", paquete);

        return "admin/Paquete/consultar";
    }
}

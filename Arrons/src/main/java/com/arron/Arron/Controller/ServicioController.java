package com.arron.Arron.Controller;

import com.arron.Arron.Model.Servicio;
import com.arron.Arron.Service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // ------------------------------------------------
    // LISTAR SERVICIOS
    // ------------------------------------------------
    @GetMapping
    public String listarServicios(Model model) {
        model.addAttribute("servicios", servicioService.listarTodos());
        return "admin/servicios/index";
    }

    // ------------------------------------------------
    // MOSTRAR FORMULARIO CREAR
    // ------------------------------------------------
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "admin/servicios/crear";
    }

    // ------------------------------------------------
    // GUARDAR SERVICIO
    // ------------------------------------------------
    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute("servicio") Servicio servicio) {
        servicioService.guardar(servicio);
        return "redirect:/servicios";
    }

    // ------------------------------------------------
    // MOSTRAR FORMULARIO EDITAR
    // ------------------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Servicio servicio = servicioService.buscarPorId(id);

        if (servicio == null) {
            return "redirect:/servicios?error=no_existe";
        }

        model.addAttribute("servicio", servicio);
        return "admin/servicios/editar";
    }

    // ------------------------------------------------
    // ACTUALIZAR SERVICIO
    // ------------------------------------------------
    @PostMapping("/actualizar")
    public String actualizarServicio(@ModelAttribute("servicio") Servicio servicio) {
        servicioService.guardar(servicio);
        return "redirect:/servicios";
    }

    // ------------------------------------------------
    // ELIMINAR SERVICIO
    // ------------------------------------------------
    @PostMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.eliminar(id);
        return "redirect:/servicios";
    }

    // ------------------------------------------------
    // CONSULTAR SERVICIO
    // ------------------------------------------------
    @GetMapping("/consultar/{id}")
    public String consultarServicio(@PathVariable Long id, Model model) {

        Servicio servicio = servicioService.buscarPorId(id);

        if (servicio == null) {
            return "redirect:/servicios?error=no_existe";
        }

        model.addAttribute("servicio", servicio);
        return "admin/servicios/consultar";
    }
}
package com.arron.Arron.Controller;

import com.arron.Arron.Model.Producto;
import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ----------------------------------------
    // LISTAR
    // ----------------------------------------
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "admin/productos/index";
    }

    // ----------------------------------------
    // FORMULARIO CREAR
    // ----------------------------------------
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/productos/crear";
    }

    // ----------------------------------------
    // GUARDAR
    // ----------------------------------------
    @PostMapping("/guardar")
    public String guardarProducto(
            @ModelAttribute Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            HttpSession session
    ) {
        try {
            if (!imagenFile.isEmpty()) {
                producto.setImagen(imagenFile.getBytes());
            }

            // ✅ USUARIO LOGUEADO
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            producto.setIdUsuario(usuario.getId());
            producto.setFechaEntrada(LocalDate.now());

            productoService.guardarProducto(producto);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/productos/crear?error";
        }

        return "redirect:/productos";
    }


    // ----------------------------------------
    // EDITAR
    // ----------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        return "admin/productos/editar";
    }

    // ----------------------------------------
    // ACTUALIZAR
    // ----------------------------------------
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.actualizarProducto(producto);
        return "redirect:/productos";
    }

    // ----------------------------------------
    // ELIMINAR
    // ----------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }

    // ----------------------------------------
    // CONSULTAR
    // ----------------------------------------
    @GetMapping("/consultar/{id}")
    public String consultarProducto(@PathVariable Long id, Model model) {

        Producto producto = productoService.obtenerPorId(id);

        if (producto == null) {
            return "redirect:/productos?error=no_existe";
        }

        model.addAttribute("producto", producto);

        return "admin/productos/consultar";
    }
}

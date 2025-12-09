package com.arron.Arron.Controller;

import com.arron.Arron.Model.Producto;
import com.arron.Arron.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String guardarProducto(@ModelAttribute Producto producto) throws Exception {

        MultipartFile archivo = producto.getImagen();

        if (archivo != null && !archivo.isEmpty()) {
            producto.setImagenBD(archivo.getBytes());
        }

        productoService.guardarProducto(producto);

        return "redirect:/productos";
    }

    // ----------------------------------------
    // FORMULARIO EDITAR
    // ----------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Producto producto = productoService.obtenerPorId(id);

        if (producto == null) {
            return "redirect:/productos?error=no_existe";
        }

        model.addAttribute("producto", producto);
        return "admin/productos/editar";
    }

    // ----------------------------------------
    // ACTUALIZAR
    // ----------------------------------------
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) throws Exception {

        Producto productoBD = productoService.obtenerPorId(producto.getIdProducto());

        if (productoBD == null) {
            return "redirect:/productos?error=no_existe";
        }

        MultipartFile archivoNuevo = producto.getImagen();

        if (archivoNuevo != null && !archivoNuevo.isEmpty()) {
            productoBD.setImagenBD(archivoNuevo.getBytes());
        }

        productoBD.setNombreProducto(producto.getNombreProducto());
        productoBD.setDescripcion(producto.getDescripcion());
        productoBD.setExistencia(producto.getExistencia());
        productoBD.setPrecioCompra(producto.getPrecioCompra());
        productoBD.setPrecioAlquiler(producto.getPrecioAlquiler());
        productoBD.setFechaEntrada(producto.getFechaEntrada());
        productoBD.setFechaSalida(producto.getFechaSalida());

        productoService.actualizarProducto(productoBD);

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

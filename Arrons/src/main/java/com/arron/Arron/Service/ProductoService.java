package com.arron.Arron.Service;

import com.arron.Arron.Model.Producto;
import com.arron.Arron.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ✅ LISTAR
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // ✅ GUARDAR
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    // ✅ BUSCAR POR ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // ✅ ACTUALIZAR
    public void actualizarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    // ✅ ELIMINAR
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

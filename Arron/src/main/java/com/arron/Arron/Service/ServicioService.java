package com.arron.Arron.Service;

import com.arron.Arron.Model.Servicio;
import com.arron.Arron.Repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    // LISTAR
    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    // BUSCAR POR ID ✅ Long
    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    // GUARDAR / ACTUALIZAR
    public Servicio guardar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    // ELIMINAR ✅ Long
    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }
}

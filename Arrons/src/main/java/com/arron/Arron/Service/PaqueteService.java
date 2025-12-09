package com.arron.Arron.Service;

import com.arron.Arron.Model.Paquete;
import com.arron.Arron.Repository.PaqueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;

    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public List<Paquete> listarTodos() {
        return paqueteRepository.findAll();
    }

    public Paquete guardar(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    public Paquete buscarPorId(Long id) {
        return paqueteRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        paqueteRepository.deleteById(id);
    }
}
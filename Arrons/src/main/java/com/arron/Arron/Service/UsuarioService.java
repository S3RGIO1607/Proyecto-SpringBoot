package com.arron.Arron.Service;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ================================
    // BUSCAR POR DOCUMENTO (LOGIN)
    // ================================
    public Usuario buscarPorNumeroDocumento(Long numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento).orElse(null);
    }

    // ================================
    // LISTAR
    // ================================
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // ================================
    // OBTENER POR ID
    // ================================
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // ================================
    // GUARDAR USUARIO (por si otro código lo usa)
    // ================================
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ================================
    // ACTUALIZAR
    // ================================
    public void actualizarUsuario(Usuario usuario) {

        // Obtener el usuario actual en BD
        Usuario usuarioBD = usuarioRepository.findById(usuario.getId()).orElse(null);

        if (usuarioBD == null) {
            return; // o lanza excepción si quieres
        }

        // Mantener la contraseña existente (no permitir cambiarla aquí)
        usuario.setContrasena(usuarioBD.getContrasena());

        // Guardar el resto de cambios
        usuarioRepository.save(usuario);
    }

    // ================================
    // ELIMINAR
    // ================================
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ================================
    // CONTAR USUARIOS (NECESARIO PARA EL DATALOADER)
    // ================================
    public long count() {
        return usuarioRepository.count();
    }

    // ================================
    // VALIDAR SI EXISTE POR DOCUMENTO (REGISTRO)
    // ================================
    public boolean existePorDocumento(Long numeroDocumento) {
        return usuarioRepository.existsByNumeroDocumento(numeroDocumento);
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

}

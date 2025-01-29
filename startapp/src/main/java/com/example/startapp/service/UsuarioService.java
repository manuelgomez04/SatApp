package com.example.startapp.service;

import com.example.startapp.dto.EditUsuarioDTO;
import com.example.startapp.model.Usuario;
import com.example.startapp.repo.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public List<Usuario> getAllUsuarios() {
        List<Usuario> result = usuarioRepository.findAll();

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron usuarios");
        } else {
            return result;
        }
    }

    public Usuario getUsuarioById(Long id) {
        Usuario result = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return result;
    }

    public Usuario saveUsuario(EditUsuarioDTO nuevoUsuario) {


        return usuarioRepository.save(Usuario.builder()
                .nombre(nuevoUsuario.nombre())
                .email(nuevoUsuario.email())
                .role(nuevoUsuario.role())
                .password(nuevoUsuario.password())
                .username(nuevoUsuario.username())
                .build());
    }

    public Usuario updateUsuario(Long id, EditUsuarioDTO editUsuarioDTO) {
        return usuarioRepository.findById(id).map(old -> {
            old.setNombre(editUsuarioDTO.nombre());
            old.setEmail(editUsuarioDTO.email());
            old.setRole(editUsuarioDTO.role());
            old.setPassword(editUsuarioDTO.password());
            old.setUsername(editUsuarioDTO.username());
            return usuarioRepository.save(old);
        }).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


}

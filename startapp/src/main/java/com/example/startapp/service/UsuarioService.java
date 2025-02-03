package com.example.startapp.service;

import com.example.startapp.dto.EditUsuarioDto;
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



    public void deleteUsuario (Long id) {
        Usuario usuario = getUsuarioById(id);
        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }


}

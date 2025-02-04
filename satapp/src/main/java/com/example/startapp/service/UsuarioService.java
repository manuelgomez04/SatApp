package com.example.startapp.service;

import com.example.startapp.dto.EditUsuarioDto;
import com.example.startapp.error.UsuarioNotFoundException;
import com.example.startapp.model.Usuario;
import com.example.startapp.repo.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public List<Usuario> getAllUsuarios() {
        List<Usuario> result = usuarioRepository.findAll();

        if (result.isEmpty()) {
            throw new UsuarioNotFoundException("No se encontraron usuarios");
        } else {
            return result;
        }
    }

    public Usuario getUsuarioById(Long id) {
        Optional <Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsuarioNotFoundException("Usuario no encontrado");
        } else {
            return user.get();
        }

    }



    public void deleteUsuario (Long id) {
        Usuario usuario = getUsuarioById(id);
        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }


}

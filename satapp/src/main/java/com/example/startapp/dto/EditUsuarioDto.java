package com.example.startapp.dto;

import com.example.startapp.model.Usuario;

public record EditUsuarioDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {

    public static GetUsuarioDto of(Usuario usuario){
        return new GetUsuarioDto(
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRole()
        );
    }
}

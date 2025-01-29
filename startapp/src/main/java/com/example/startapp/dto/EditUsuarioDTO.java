package com.example.startapp.dto;

import com.example.startapp.model.Usuario;

public record EditUsuarioDTO(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {


}

package com.example.startapp.dto;

import com.example.startapp.model.Tipo;

public record EditPersonalDto(
        String nombre,
        String username,
        String email,
        String password,
        String role,
        Tipo tipo
) {
}

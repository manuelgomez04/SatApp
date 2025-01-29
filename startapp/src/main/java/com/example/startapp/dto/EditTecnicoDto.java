package com.example.startapp.dto;

public record EditTecnicoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {
}

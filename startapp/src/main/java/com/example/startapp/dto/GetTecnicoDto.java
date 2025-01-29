package com.example.startapp.dto;

public record GetTecnicoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {
}

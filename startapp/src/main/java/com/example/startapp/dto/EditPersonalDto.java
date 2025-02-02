package com.example.startapp.dto;

public record EditPersonalDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {
}

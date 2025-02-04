package com.example.startapp.dto;

public record EditEquipoDto(
        String nombre,
        String caracteristicas,
        Long ubicacionId
) {
}

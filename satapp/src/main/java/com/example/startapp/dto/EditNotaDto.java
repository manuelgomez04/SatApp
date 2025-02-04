package com.example.startapp.dto;

import com.example.startapp.model.Incidencia;

import java.time.LocalDate;

public record EditNotaDto(
        Incidencia incidencia,
        LocalDate fecha,
        String contenido,
        String autor
) {
}

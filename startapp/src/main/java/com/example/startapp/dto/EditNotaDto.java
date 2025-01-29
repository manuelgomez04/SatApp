package com.example.startapp.dto;

import java.time.LocalDate;

public record EditNotaDto(
        Long incidenciaId,
        LocalDate fecha,
        String contenido,
        String autor
) {
}

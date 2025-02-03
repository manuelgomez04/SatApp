package com.example.startapp.dto;

import com.example.startapp.model.Nota;

import java.time.LocalDate;

public record GetNotaDto(
        Long incidenciaId,
        LocalDate fecha,
        String contenido,
        String autor
) {

    public static GetNotaDto of(Nota nota) {
        return new GetNotaDto(
                nota.getIncidencia().getId(), nota.getFecha(), nota.getContenido(), nota.getAutor()
        );
    }
}

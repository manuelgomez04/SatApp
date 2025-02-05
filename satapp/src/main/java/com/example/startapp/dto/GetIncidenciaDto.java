package com.example.startapp.dto;

import com.example.startapp.model.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record GetIncidenciaDto(

        LocalDate fecha,
        String titulo,
        String descripcion,
        Estado estado,
        Boolean urgencia,
        List<GetCategoriaDto> categorias,
        List<GetEquipoDto> equipos,
        List<GetNotaDto> notas,
        GetUbicacionDto ubicacion,
        GetUsuarioDto usuario
) {

    public static GetIncidenciaDto of(Incidencia incidencia) {
        if (incidencia == null) {
            return null; // Si la incidencia en sí es null, devolvemos null
        }
        return new GetIncidenciaDto(
                (incidencia.getFecha() != null) ? incidencia.getFecha() : null,
                (incidencia.getTitulo() != null) ? incidencia.getTitulo() : "",
                (incidencia.getDescripcion() != null) ? incidencia.getDescripcion() : "",
                (incidencia.getEstado() != null) ? incidencia.getEstado() : Estado.ABIERTA,
                incidencia.getUrgencia(),
                (incidencia.getCategorias() != null) ?
                        incidencia.getCategorias().stream()
                                .filter(Objects::nonNull) // Evita que haya categorías nulas
                                .map(GetCategoriaDto::of)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                (incidencia.getEquipos() != null) ?
                        incidencia.getEquipos().stream()
                                .filter(Objects::nonNull)
                                .map(GetEquipoDto::of)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                (incidencia.getNotas() != null) ?
                        incidencia.getNotas().stream()
                                .filter(Objects::nonNull)
                                .map(GetNotaDto::of)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                (incidencia.getUbicacion() != null) ? GetUbicacionDto.of(incidencia.getUbicacion()) : null,
                (incidencia.getUsuario() != null) ? GetUsuarioDto.of(incidencia.getUsuario()) : null
        );
    }

}

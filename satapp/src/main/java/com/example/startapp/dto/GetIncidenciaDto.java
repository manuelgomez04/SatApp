package com.example.startapp.dto;

import com.example.startapp.model.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return new GetIncidenciaDto(
                incidencia.getFecha(),
                incidencia.getTitulo(),
                incidencia.getDescripcion(),
                incidencia.getEstado(),
                incidencia.getUrgencia(),
                incidencia.getCategorias().stream().map(GetCategoriaDto::of).toList(),
                incidencia.getEquipos().stream().map(GetEquipoDto::of).toList(),
                incidencia.getNotas().stream().map(GetNotaDto::of).toList(),
                GetUbicacionDto.of(incidencia.getUbicacion()),
                GetUsuarioDto.of(incidencia.getUsuario())
        );
    }

}

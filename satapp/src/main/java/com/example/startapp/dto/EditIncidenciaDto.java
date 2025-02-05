package com.example.startapp.dto;

import com.example.startapp.model.*;

import java.time.LocalDate;
import java.util.List;

public record EditIncidenciaDto(
        LocalDate fecha,
        String titulo,
        String descripcion,
        Estado estado,
        Boolean urgencia,
        List<Categoria> categorias,
        List<Equipo> equipos,
        List<Nota> notas,
        Ubicacion ubicacion,
        Usuario usuario
) {
}

package com.example.startapp.dto;

import com.example.startapp.model.HistoricoCursos;

import java.util.List;

public record EditAlumnoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role,
        List<HistoricoCursos> historicoCursos
) {
}

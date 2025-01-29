package com.example.startapp.dto;

import com.example.startapp.model.Alumno;
import com.example.startapp.model.HistoricoCursos;
import com.example.startapp.model.Usuario;

import java.util.List;

public record GetAlumnoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role,
        List<HistoricoCursos> historicoCursos
) {
    public static GetAlumnoDto of(Alumno alumno){
        return new GetAlumnoDto(
                alumno.getNombre(),
                alumno.getUsername(),
                alumno.getEmail(),
                alumno.getPassword(),
                alumno.getRole(),
                alumno.getHistoricoCursos()

        );
    }
}

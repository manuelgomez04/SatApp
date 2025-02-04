package com.example.startapp.dto;

import com.example.startapp.model.Equipo;

import java.util.List;

public record GetEquipoDto(
        String nombre,
        String caracteristicas
) {
    public static GetEquipoDto of(Equipo equipo) {
        return new GetEquipoDto(equipo.getNombre(), equipo.getCaracteristicas());
    }
}

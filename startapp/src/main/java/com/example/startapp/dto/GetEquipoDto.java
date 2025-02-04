package com.example.startapp.dto;

import com.example.startapp.model.Equipo;
import com.example.startapp.model.Ubicacion;

import java.util.List;

public record GetEquipoDto(
        String nombre,
        String caracteristicas,
        Long ubicacionId
) {
    public static GetEquipoDto of(Equipo equipo) {
        return new GetEquipoDto(
                equipo.getNombre(),
                equipo.getCaracteristicas(),
                equipo.getUbicacion().getId()
        );
    }
}

package com.example.startapp.dto;

import com.example.startapp.model.Ubicacion;

public record GetUbicacionDto(
        String nombre
) {
    public static GetUbicacionDto of(Ubicacion ubicacion){
        return new GetUbicacionDto(
                ubicacion.getNombre()
        );
    }
}

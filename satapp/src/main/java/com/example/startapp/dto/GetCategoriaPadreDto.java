package com.example.startapp.dto;

import com.example.startapp.model.Categoria;

public record GetCategoriaPadreDto(
        String nombre
) {
    public static GetCategoriaPadreDto of(Categoria categoriaPadre) {
        return new GetCategoriaPadreDto(
                categoriaPadre.getNombre()
        );
    }
}

package com.example.startapp.dto;

import com.example.startapp.model.Categoria;

public record GetSubCategoriaDto (
        String nombre
){
    public static GetSubCategoriaDto of(Categoria categoria){
        return new GetSubCategoriaDto(
                categoria.getNombre()
        );
    }
}

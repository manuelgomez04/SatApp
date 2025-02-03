package com.example.startapp.dto;

import com.example.startapp.model.Categoria;

import java.util.List;

public record GetCategoriaDto(
        String nombre,
        List<Categoria> categorias,
        Categoria categoriaPadre

) {
    public static GetCategoriaDto of (Categoria categoria){
        return new GetCategoriaDto(
                categoria.getNombre(),
                categoria.getSubCategorias(),
                categoria.getCategoriaPadre()


        );
    }
}

package com.example.startapp.dto;

import com.example.startapp.model.Categoria;

import java.util.List;

public record EditCategoriaDto(
        String nombre,
        List<Categoria>subCategorias,
        Categoria categoriaPadre
        ) {
}

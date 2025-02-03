package com.example.startapp.dto;

import com.example.startapp.model.Categoria;

import java.util.List;

public record GetCategoriaDto(
        String nombre,
        List<GetSubCategoriaDto> categorias, // Lista de subcategorías (evitando bucles)
        GetCategoriaPadreDto categoriaPadre // Evita referencias infinitas
) {
    public static GetCategoriaDto of(Categoria categoria) {
        return new GetCategoriaDto(
                categoria.getNombre(),
                categoria.getSubCategorias().stream().map(GetSubCategoriaDto::of).toList(),
                categoria.getCategoriaPadre() != null ? GetCategoriaPadreDto.of(categoria.getCategoriaPadre()) : null
        );
    }
}



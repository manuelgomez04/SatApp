package com.example.startapp.controller;

import com.example.startapp.dto.EditCategoriaDto;
import com.example.startapp.dto.GetCategoriaDto;
import com.example.startapp.model.Categoria;
import com.example.startapp.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")

public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<GetCategoriaDto> getCategorias() {
        return categoriaService.getAllCategorias().stream().map(GetCategoriaDto::of).toList();
    }

    @PostMapping
    public GetCategoriaDto saveCategoria(@RequestBody EditCategoriaDto categoria) {
        return GetCategoriaDto.of(categoriaService.saveCategoria(categoria));
    }


}

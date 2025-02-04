package com.example.startapp.service;

import com.example.startapp.dto.EditCategoriaDto;
import com.example.startapp.error.CategoriaNotFoundException;
import com.example.startapp.model.Categoria;
import com.example.startapp.repo.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        List<Categoria> result = categoriaRepository.findAllCat();

        if (result.isEmpty()) {
            throw new CategoriaNotFoundException("No se encontraron categorias");
        } else {
            return result;
        }
    }

    public Categoria getCategoriaById(Long id) {
        Optional<Categoria> result = categoriaRepository.findByIdCat(id);

        if (result.isEmpty()) {
            throw new CategoriaNotFoundException("Categoria no encontrada");
        } else {
            return result.get();
        }
    }


    public Categoria saveCategoria(EditCategoriaDto categoriaDto) {
        Categoria categoriaPadre = null;
        if (categoriaDto.categoriaPadre() != null && categoriaDto.categoriaPadre().getId() != null) {
            categoriaPadre = categoriaRepository.findById(categoriaDto.categoriaPadre().getId())
                    .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría padre con ID: " + categoriaDto.categoriaPadre().getId()));
        }

        Categoria nuevaCategoria = categoriaRepository.save(
                Categoria.builder()
                        .nombre(categoriaDto.nombre())
                        .categoriaPadre(categoriaPadre)
                        .build()
        );

        if (categoriaDto.subCategorias() != null && !categoriaDto.subCategorias().isEmpty()) {
            List<Categoria> subCategorias = categoriaDto.subCategorias().stream()
                    .map(subCatDto -> categoriaRepository.findById(subCatDto.getId())
                            .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la subcategoría con ID: " + subCatDto.getId()))
                    )
                    .toList();

            nuevaCategoria.setSubCategorias(subCategorias);
        }

        return categoriaRepository.save(nuevaCategoria);


    }


    @Transactional
    public Categoria editCategoria(Long id, EditCategoriaDto editCategoriaDto) {
        return categoriaRepository.findByIdCat(id)
                .map(categoria -> {
                    categoria.setNombre(editCategoriaDto.nombre());
                    categoria.setCategoriaPadre(editCategoriaDto.categoriaPadre());
                    categoria.setSubCategorias(editCategoriaDto.subCategorias());


                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException("No se ha encontrado ninguna categoria con ese id"));


    }


}




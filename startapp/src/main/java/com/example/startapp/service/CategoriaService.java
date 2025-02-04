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
            Optional<Categoria> optionalPadre = categoriaRepository.findById(categoriaDto.categoriaPadre().getId());
            if (optionalPadre.isEmpty()) {
                throw new CategoriaNotFoundException("Categoría padre con ID " + categoriaDto.categoriaPadre().getId() + " no encontrada");
            }
            categoriaPadre = optionalPadre.get();
        }

        Categoria cNueva = Categoria.builder()
                .nombre(categoriaDto.nombre())
                .categoriaPadre(categoriaPadre)
                .build();

        cNueva = categoriaRepository.saveAndFlush(cNueva);

        for (Categoria subCategoriaDto : categoriaDto.subCategorias()) {
            if (subCategoriaDto.getId() != null) {
                Optional<Categoria> optionalSubCategoria = categoriaRepository.findById(subCategoriaDto.getId());
                if (optionalSubCategoria.isEmpty()) {
                    throw new CategoriaNotFoundException("Subcategoría con ID " + subCategoriaDto.getId() + " no encontrada");
                }

                Categoria subCategoria = optionalSubCategoria.get();

                if (subCategoria.getCategoriaPadre() != null) {
                    throw new CategoriaNotFoundException("La subcategoría con ID " + subCategoriaDto.getId() + " ya tiene una categoría padre");
                }

                subCategoria.setCategoriaPadre(cNueva);
                categoriaRepository.save(subCategoria);
            }
        }

        return cNueva;
    }


    public Categoria editCategoria(Long id, EditCategoriaDto editCategoriaDto) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findByIdCat(id);
        if (categoriaOpt.isEmpty()) {
            throw new CategoriaNotFoundException("No se ha encontrado ninguna categoría con ese id");
        }

        Categoria categoria = categoriaOpt.get();


        categoria.setNombre(editCategoriaDto.nombre());


        if (editCategoriaDto.categoriaPadre() != null && editCategoriaDto.categoriaPadre().getId() != null) {
            Optional<Categoria> categoriaPadreOpt = categoriaRepository.findById(editCategoriaDto.categoriaPadre().getId());
            if (categoriaPadreOpt.isEmpty()) {
                throw new RuntimeException("La categoría padre con ID " + editCategoriaDto.categoriaPadre().getId() + " no existe.");
            }
            categoria.setCategoriaPadre(categoriaPadreOpt.get());
        } else {
            categoria.setCategoriaPadre(null);
        }

        categoria.getSubCategorias().clear();
        for (Categoria subCategoriaDto : editCategoriaDto.subCategorias()) {
            if (subCategoriaDto.getId() != null) {
                Optional<Categoria> subCategoriaOpt = categoriaRepository.findById(subCategoriaDto.getId());
                if (subCategoriaOpt.isEmpty()) {
                    throw new RuntimeException("Subcategoría con ID " + subCategoriaDto.getId() + " no encontrada.");
                }

                Categoria subCategoria = subCategoriaOpt.get();
                if (subCategoria.getCategoriaPadre() != null) {
                    throw new RuntimeException("La subcategoría con ID " + subCategoriaDto.getId() + " ya tiene un padre.");
                }

                categoria.addSubCategoria(subCategoria);
            }
        }

        return categoriaRepository.save(categoria);
    }



}




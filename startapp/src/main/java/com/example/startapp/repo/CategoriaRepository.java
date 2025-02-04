package com.example.startapp.repo;

import com.example.startapp.model.Alumno;
import com.example.startapp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("""
            select c from Categoria c
            left join fetch c.subCategorias
            """)
    List<Categoria> findAllCat();

    @Query("""
            select c from Categoria c
            join fetch c.subCategorias
            where c.id = :id
            """)
    Optional<Categoria> findByIdHis(Long id);
}

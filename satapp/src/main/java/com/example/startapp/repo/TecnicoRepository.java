package com.example.startapp.repo;

import com.example.startapp.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    @Query("""
            select t from Tecnico t
            join fetch t.gestionarIncidencias
            where t.id = :id
            """)
    Optional<Tecnico> findAllGes(@Param("id") Long id);
}

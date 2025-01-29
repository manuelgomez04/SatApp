package com.example.startapp.repo;

import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {


    @Query("""
        select n
        from Incidencia i join i.notas n
        """)
    List<Nota> findAllNotas();


    @Query("""
            select n
            from Incidencia i join i.notas n
            where n.id = ?1
            """)
    Optional<Nota> findByIdNota(Long id);



}

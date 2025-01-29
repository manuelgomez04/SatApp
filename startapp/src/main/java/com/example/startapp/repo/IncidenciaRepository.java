package com.example.startapp.repo;

import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    /*
    @Query"""
    select i
    from Incidencia i join fetch i.notas """
    */


    @Query("""
        select n
        from Incidencia i join fetch i.notas n
        """)
    List<Nota> findAllNotas();




}

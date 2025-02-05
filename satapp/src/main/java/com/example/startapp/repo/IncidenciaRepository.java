package com.example.startapp.repo;

import com.example.startapp.dto.*;
import com.example.startapp.model.Categoria;

import com.example.startapp.model.Equipo;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import com.example.startapp.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
            from Nota n
            where n.id = ?1
            """)
    Optional<Nota> findByIdNota(Long id);

    @Query("""
        select i 
        from Incidencia i 
        where i.ubicacion.id = ?1
        """)
    List<Incidencia> findByUbicacionId(Long ubicacionId);

    @Query("""
        select i 
        from Incidencia i join i.equipos e
        where e.id = ?1
        """)
    List<Incidencia> findByEquipoId(Long equipoId);


}

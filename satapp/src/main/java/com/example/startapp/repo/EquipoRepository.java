package com.example.startapp.repo;

import com.example.startapp.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("""
            select e 
            from Equipo e 
            where e.ubicacion.id = ?1
            """)
    List<Equipo> findByUbicacionId(Long ubicacionId);
}

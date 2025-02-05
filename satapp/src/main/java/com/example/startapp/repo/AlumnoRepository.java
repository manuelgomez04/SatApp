package com.example.startapp.repo;

import com.example.startapp.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {


    @Query("""
            select a from Alumno a
            left join fetch a.historicoCursos
            """)
    List<Alumno> findAllHis();

    @Query("""
            select a from Alumno a
            left join fetch a.historicoCursos
            where a.id = :id
            """)
    Optional <Alumno> findByIdHis(Long id);
}

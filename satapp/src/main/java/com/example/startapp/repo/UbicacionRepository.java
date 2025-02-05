package com.example.startapp.repo;

import com.example.startapp.model.Ubicacion;
import com.example.startapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.incidencias WHERE u.id = :id")
    Optional<Usuario> findByIdWithIncidencias(@Param("id") Long id);

}

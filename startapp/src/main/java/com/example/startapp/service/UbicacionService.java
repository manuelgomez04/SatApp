package com.example.startapp.service;

import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.error.UbicacionNotFoundException;
import com.example.startapp.model.Ubicacion;
import com.example.startapp.repo.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public List<Ubicacion> findAll() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();

        if (ubicaciones.isEmpty()) {
            throw new UbicacionNotFoundException("Ubicación no encontrada");
        }
        return ubicaciones;
    }

    public Ubicacion findById(Long ubicaId) {
        Optional<Ubicacion> ubicacion = ubicacionRepository.findById(ubicaId);
        if (ubicacion.isEmpty()) {
            throw new UbicacionNotFoundException("Ubicación no encontrada");
        }
        return ubicacion.get();
    }

    public Ubicacion saveUbicacion(GetUbicacionDto nuevo) {

        Ubicacion ubi = Ubicacion.builder().nombre(nuevo.nombre()).build();
        return ubicacionRepository.save(ubi);
    }

    public void deleteUbicacion(Long idUbi) {
        ubicacionRepository.deleteById(idUbi);
    }

}

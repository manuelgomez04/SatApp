package com.example.startapp.service;

import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.model.Ubicacion;
import com.example.startapp.repo.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;


    public Ubicacion saveUbicacion(GetUbicacionDto nuevo) {

        Ubicacion ubi = Ubicacion.builder().nombre(nuevo.nombre()).build();
        return ubicacionRepository.save(ubi);

    }

}

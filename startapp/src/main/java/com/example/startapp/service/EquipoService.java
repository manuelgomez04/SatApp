package com.example.startapp.service;

import com.example.startapp.dto.GetEquipoDto;
import com.example.startapp.error.EquipoNotFoundException;
import com.example.startapp.model.Equipo;
import com.example.startapp.repo.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        List<Equipo> results = equipoRepository.findAll();
        if (results.isEmpty()) {
            throw new EquipoNotFoundException("Equipos no encontrado");
        }
        return results;
    }

    public Equipo saveEquipo(GetEquipoDto getEquipoDto) {

        Equipo equipo = Equipo.builder()
                .nombre(getEquipoDto.nombre())
                .caracteristicas(getEquipoDto.caracteristicas())
                .build();
        return equipoRepository.save(equipo);
    }

    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }

}

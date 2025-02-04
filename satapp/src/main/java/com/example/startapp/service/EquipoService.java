package com.example.startapp.service;

import com.example.startapp.dto.EditEquipoDto;
import com.example.startapp.dto.GetEquipoDto;
import com.example.startapp.error.EquipoNotFoundException;
import com.example.startapp.model.Equipo;
import com.example.startapp.repo.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Equipo findById(Long id) {
        Optional<Equipo> equipo = equipoRepository.findById(id);

        if (equipo.isEmpty()) {
            throw new EquipoNotFoundException("Equipo no encontrado");
        }
        return equipo.get();

    }

    public Equipo saveEquipo(GetEquipoDto getEquipoDto) {

        Equipo equipo = Equipo.builder()
                .nombre(getEquipoDto.nombre())
                .caracteristicas(getEquipoDto.caracteristicas())
                .build();
        return equipoRepository.save(equipo);
    }

    public Equipo editEquipo(Long id, EditEquipoDto nuevo) {

        Optional<Equipo> antiguo = equipoRepository.findById(id);

        if (antiguo.isEmpty()) {
            throw new EquipoNotFoundException("Equipo no encontrado");
        }

        antiguo.get().setNombre(nuevo.nombre());
        antiguo.get().setCaracteristicas(nuevo.caracteristicas());

        return equipoRepository.save(antiguo.get());
    }

    public void deleteEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

}

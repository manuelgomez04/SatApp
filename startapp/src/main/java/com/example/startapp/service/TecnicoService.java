package com.example.startapp.service;


import com.example.startapp.dto.EditTecnicoDto;
import com.example.startapp.dto.GetTecnicoDto;
import com.example.startapp.error.TecnicoNotFoundException;
import com.example.startapp.model.Tecnico;
import com.example.startapp.repo.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;


    public List<Tecnico> getAllTecnicos() {
        List<Tecnico> result = tecnicoRepository.findAll();

        if (result.isEmpty()) {
            throw new TecnicoNotFoundException("No se encontraron tecnicos");
        } else {
            return result;
        }
    }

    public Tecnico getTecnicoById(Long id) {
        Tecnico result = tecnicoRepository
                .findById(id)
                .orElseThrow(() -> new TecnicoNotFoundException("Tecnico no encontrado"));
        return result;
    }

    public Tecnico saveTecnico(EditTecnicoDto nuevoTecnico) {
        return tecnicoRepository.save(Tecnico.builder()
                .nombre(nuevoTecnico.nombre())
                .email(nuevoTecnico.email())
                .role(nuevoTecnico.role())
                .password(nuevoTecnico.password())
                .username(nuevoTecnico.username())
                .build());
    }


    public Tecnico editTecnico(Long id, EditTecnicoDto editTecnicoDto) {
        return tecnicoRepository.findById(id).map(old ->
        {
            old.setNombre(editTecnicoDto.nombre());
            old.setEmail(editTecnicoDto.email());
            old.setRole(editTecnicoDto.role());
            old.setPassword(editTecnicoDto.password());
            old.setUsername(editTecnicoDto.username());
            return tecnicoRepository.save(old);
        }).orElseThrow(() -> new EntityNotFoundException("Tecnico no encontrado"));
    }

}

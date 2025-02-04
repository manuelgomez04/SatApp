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
import java.util.Optional;

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

       Optional<Tecnico> result = tecnicoRepository.findById(id);
       if (result.isEmpty()) {
           throw new TecnicoNotFoundException("Tecnico no encontrado");}
       else {
              return result.get();
       }


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

        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isEmpty()) {
            throw new TecnicoNotFoundException("Tecnico no encontrado");
        } else {
            tecnico.map(t -> {
                t.setNombre(editTecnicoDto.nombre());
                t.setEmail(editTecnicoDto.email());
                t.setRole(editTecnicoDto.role());
                t.setPassword(editTecnicoDto.password());
                t.setUsername(editTecnicoDto.username());
                return tecnicoRepository.save(t);
            });

        }
        return tecnico.get();


    }

}

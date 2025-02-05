package com.example.startapp.service;


import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.EditTecnicoDto;
import com.example.startapp.dto.GetTecnicoDto;
import com.example.startapp.error.IncidenciaNotFoundException;
import com.example.startapp.error.TecnicoNotFoundException;
import com.example.startapp.model.Estado;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Tecnico;
import com.example.startapp.repo.IncidenciaRepository;
import com.example.startapp.repo.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final IncidenciaRepository incidenciaRepository;


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
            throw new TecnicoNotFoundException("Tecnico no encontrado");
        } else {
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

    public Incidencia gestionarIncidencia(Long id, EditIncidenciaDto incidenciaDto) {
        Optional<Incidencia> optionalIncidencia = incidenciaRepository.findById(id);

        if (optionalIncidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("No se ha encontrado incidencia con ese id");
        }

        Incidencia incidenciaEncontrada = optionalIncidencia.get();

        incidenciaEncontrada.setEstado(incidenciaDto.estado());

        if (incidenciaEncontrada.getEstado() == Estado.CERRADA) {
            incidenciaRepository.delete(incidenciaEncontrada);
            return null;

        }
        return incidenciaRepository.save(incidenciaEncontrada);
    }

    @Transactional
    public Tecnico addIncidencia(Long id, Long idIncidencia) {
        Optional<Tecnico> tecnico = tecnicoRepository.findAllGes(id);
        Optional<Incidencia> incidencia = incidenciaRepository.findById(idIncidencia);

        if (tecnico.isEmpty()) {
            throw new TecnicoNotFoundException("Tecnico no encontrado");
        }
        if (incidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("Incidencia no encontrada");
        }

        Tecnico tecnicoEncontrado = tecnico.get();

         // Esto inicializa la colección

        // Añadir la incidencia a la colección
        tecnicoEncontrado.getIncidencias().add(incidencia.get());

        return tecnicoRepository.save(tecnicoEncontrado);
    }

}

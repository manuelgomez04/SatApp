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
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @Transactional
    public Incidencia gestionarIncidencia(Long id, EditIncidenciaDto incidenciaDto) {
        Optional <Incidencia>  incidenciaOpt = incidenciaRepository.findByIdSimple(id);

        if (incidenciaOpt.isEmpty()) {
                throw new IncidenciaNotFoundException("No se ha encontrado incidencia con ese id");
        }
        Incidencia incidencia = incidenciaOpt.get();

        incidencia.getCategorias().forEach(categoria -> categoria.getSubCategorias().size());
        incidencia.getCategorias().size();
        incidencia.getEquipos().size();
        incidencia.getNotas().size();
        incidencia.setEstado(incidenciaDto.estado());

        if (incidencia.getEstado() == Estado.CERRADA) {
            incidenciaRepository.delete(incidencia);
            return null;
        }

        // Guardamos la incidencia
        return incidenciaRepository.save(incidencia);
    }




    @Transactional
    public Tecnico addIncidencia(Long idTecnico, Long idIncidencia) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(idTecnico);
        Optional<Incidencia> incidencia = incidenciaRepository.findById(idIncidencia);

        if (tecnico.isEmpty()) {
            throw new TecnicoNotFoundException("Tecnico no encontrado");
        }
        if (incidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("Incidencia no encontrada");
        }

        Tecnico tecnicoEncontrado = tecnico.get();


        tecnicoEncontrado.getIncidencias().add(incidencia.get());

        return tecnicoRepository.save(tecnicoEncontrado);
    }

}

package com.example.startapp.service;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import com.example.startapp.repo.IncidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotaService {

    private final IncidenciaRepository incidenciaRepository;

    public List<Nota> findAll() {

        List<Nota> notas = incidenciaRepository.findAllNotas();

        if (notas.isEmpty()) {
            throw new EntityNotFoundException("No se han encontrado notas");
        }

        return notas;
    }

    public Nota findNotaById(Long id) {
        Optional<Nota> nota = incidenciaRepository.findByIdNota(id);

        if (nota.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado una nota con ese id");
        }

        return nota.get();
    }

    @Transactional
    public Nota saveNota(Long incidenciaId, EditNotaDto nuevo) {

        Optional<Incidencia> incidencia = incidenciaRepository.findById(incidenciaId);

        if (incidencia.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado la incidencia");
        }

        Nota nota = Nota.builder()
                .fecha(nuevo.fecha())
                .contenido(nuevo.contenido())
                .autor(nuevo.autor())
                .incidencia(incidencia.get())
                .build();

        incidencia.get().addNota(nota);

        incidenciaRepository.save(incidencia.get());

        return nota;
    }


    @Transactional
    public Nota editNota(Long notaId, EditNotaDto nuevo) {

        Optional<Nota> nota = incidenciaRepository.findByIdNota(notaId);

        if (nota.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado una nota con ese id");
        }

        nota.get().setAutor(nuevo.autor());
        nota.get().setFecha(nuevo.fecha());
        nota.get().setContenido(nuevo.contenido());

        incidenciaRepository.save(nota.get().getIncidencia());

        return nota.get();
    }


    @Transactional
    public void removeNota(Long notaId, Long incidenciaId) {
        Optional<Incidencia> incidencia = incidenciaRepository.findById(incidenciaId);

        if (incidencia.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado la incidencia");
        }

        incidencia.get().removeNota(findNotaById(notaId));
        incidenciaRepository.save(incidencia.get());
    }


}

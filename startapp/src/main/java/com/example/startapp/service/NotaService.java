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
        } else {
            return nota.get();
        }

    }

    @Transactional
    public Nota saveNota(Long incidenciaId, EditNotaDto nuevo) {

        Optional<Incidencia> incidenciaOptional = incidenciaRepository.findById(incidenciaId);

        if (incidenciaOptional.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado la incidencia");
        } else {
            Incidencia incidencia = incidenciaOptional.get();
            Nota nota = Nota.builder()
                    .fecha(nuevo.fecha())
                    .contenido(nuevo.contenido())
                    .autor(nuevo.autor())
                    .incidencia(incidencia)
                    .build();

            incidencia.addNota(nota);

            incidenciaRepository.save(incidencia);

            return nota;
        }


    }


    @Transactional
    public Nota editNota(Long notaId, EditNotaDto nuevo) {

        Nota nota = incidenciaRepository.findByIdNota(notaId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la nota"));

        nota.setAutor(nuevo.autor());
        nota.setFecha(nuevo.fecha());
        nota.setContenido(nuevo.contenido());

        incidenciaRepository.save(nota.getIncidencia());

        return nota;
    }


    @Transactional
    public void removeNota(Long notaId, Long incidenciaId) {
        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada con el id " + incidenciaId));
        incidencia.removeNota(findNotaById(notaId));
        incidenciaRepository.save(incidencia);
    }


}

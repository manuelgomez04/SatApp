package com.example.startapp.service;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.dto.GetNotaDto;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import com.example.startapp.model.NotaPk;
import com.example.startapp.repo.IncidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return incidenciaRepository.findByIdNota(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado una nota con ese id"));
    }

    //Se puede mejorar(DTO)
    public void save(Long incidenciaId, EditNotaDto nota) {

        Incidencia incidencia = incidenciaRepository.findById(incidenciaId).get();

        incidencia.addNota(nota);


    }

    @Transactional
    public void remove(Long notaId, Long incidenciaId) {
        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada con el id " + incidenciaId));
        incidencia.removeNota(findNotaById(notaId));
    }


}

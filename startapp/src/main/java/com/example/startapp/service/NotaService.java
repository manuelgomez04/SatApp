package com.example.startapp.service;

import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import com.example.startapp.repo.IncidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
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
/*
        List<Incidencia> listado = incidenciaRepository.findAll();

        List<Nota> results = new ArrayList<>();

        for (Incidencia incidencia : listado) {
            List<Nota> notas = incidencia.getNotas();

            if (notas.isEmpty()) {
                throw new EntityNotFoundException("No se han encontrado notas");
            }

            results.addAll(notas);
        }
        return results;

*/

        return incidenciaRepository.findAllNotas();
    }

    /*
    public Nota findById(Long id) {
        List<Incidencia> listado = incidenciaRepository.findAll();
        Optional<Nota> encontrado = Optional.ofNullable(null);

        for (Incidencia incidencia : listado) {

            List<Nota> notas = incidencia.getNotas();

            for (Nota nota : notas) {

                if (nota.getId() == id) {
                    encontrado.get() = nota;
                }
            }
        }
        return encontrado.get();
    }
     */
}

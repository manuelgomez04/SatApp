package com.example.startapp.service;

import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.GetIncidenciaDto;
import com.example.startapp.error.IncidenciaNotFoundException;
import com.example.startapp.error.NotaNotFoundException;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
import com.example.startapp.model.Usuario;
import com.example.startapp.repo.IncidenciaRepository;
import com.example.startapp.repo.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;


    public List<Incidencia> findAll() {

        List<Incidencia> incidencias = incidenciaRepository.findAllIncidencias();

        if (incidencias.isEmpty()) {
            throw new IncidenciaNotFoundException("No se han encontrado notas");
        }

        return incidencias;
    }


    public Optional<Incidencia> findById(Long id) {
        Optional<Incidencia> incidencia = incidenciaRepository.findById(id);

        if (incidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("No se ha encontrado una nota con ese id");
        }

        return incidencia;
    }


    public Incidencia abrirIncidencia(EditIncidenciaDto getIncidenciaDto) {

        Incidencia incidencia = Incidencia.builder()
                .fecha(getIncidenciaDto.fecha())
                .titulo(getIncidenciaDto.titulo())
                .descripcion(getIncidenciaDto.descripcion())
                .estado(getIncidenciaDto.estado())
                .urgencia(getIncidenciaDto.urgencia())
                .categorias(getIncidenciaDto.categorias())
                .notas(getIncidenciaDto.notas())
                .ubicacion(getIncidenciaDto.ubicacion())
                .usuario(getIncidenciaDto.usuario())

                .build();
        return incidenciaRepository.save(incidencia);

    }

}

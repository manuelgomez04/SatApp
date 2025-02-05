package com.example.startapp.service;

import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.GetCategoriaDto;
import com.example.startapp.dto.GetIncidenciaDto;
import com.example.startapp.error.*;
import com.example.startapp.model.*;
import com.example.startapp.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UbicacionRepository ubicacionRepository;
    private final CategoriaRepository categoriaRepository;
    private final EquipoRepository equipoRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<GetIncidenciaDto> getAllIncidencias() {

        List<GetIncidenciaDto> incidencias = incidenciaRepository.findAll()
                .stream()
                .map(GetIncidenciaDto::of)
                .collect(Collectors.toList());

        if (incidencias.isEmpty()) {
            throw new IncidenciaNotFoundException("No se han encontrado incidencias");
        } else {
            return incidencias;
        }

    }


    public GetIncidenciaDto findById(Long id) {
        Optional<Incidencia> incidencia = incidenciaRepository.findById(id);

        if (incidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("No se ha encontrado una nota con ese id");
        } else {
            return GetIncidenciaDto.of(incidencia.get());
        }

    }


    @Transactional
    public Incidencia abrirIncidencia(EditIncidenciaDto getIncidenciaDto) {


        Ubicacion ubicacion = null;
        if (getIncidenciaDto.ubicacion() != null) {
            Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(getIncidenciaDto.ubicacion().getId());
            if (ubicacionOpt.isPresent()) {
                ubicacion = ubicacionOpt.get();
            } else {
                throw new UbicacionNotFoundException("Ubicación no encontrada con ID: " + getIncidenciaDto.ubicacion());
            }
        }


        Usuario usuario = null;
        if (getIncidenciaDto.usuario() != null) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(getIncidenciaDto.usuario().getId());
            if (usuarioOpt.isPresent()) {
                usuario = usuarioOpt.get();
            } else {
                throw new UsuarioNotFoundException("Usuario no encontrado con ID: " + getIncidenciaDto.usuario());
            }
        }


        List<Categoria> categorias = new ArrayList<>();
        if (getIncidenciaDto.categorias() != null) {
            for (Long categoriaId : getIncidenciaDto.categorias()) {
                Optional<Categoria> categoriaOpt = categoriaRepository.findByIdCat(categoriaId);
                if (categoriaOpt.isPresent()) {
                    categorias.add(categoriaOpt.get());
                } else {
                    throw new CategoriaNotFoundException("Categoría no encontrada con ID: " + categoriaId);
                }
            }
        }


        List<Equipo> equipos = new ArrayList<>();
        if (getIncidenciaDto.equipos() != null) {
            for (Long equipoId : getIncidenciaDto.equipos()) {
                Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
                if (equipoOpt.isPresent()) {
                    equipos.add(equipoOpt.get());
                } else {
                    throw new EquipoNotFoundException("Equipo no encontrado con ID: " + equipoId);
                }
            }
        }


        Incidencia incidencia = Incidencia.builder()
                .fecha(getIncidenciaDto.fecha())
                .titulo(getIncidenciaDto.titulo())
                .descripcion(getIncidenciaDto.descripcion())
                .estado(getIncidenciaDto.estado())
                .urgencia(getIncidenciaDto.urgencia())
                .categorias(categorias)
                .equipos(equipos)
                .ubicacion(ubicacion)
                .usuario(usuario)
                .build();

        List<Nota> notas = new ArrayList<>();
        if (getIncidenciaDto.notas() != null) {
            for (Long notaId : getIncidenciaDto.notas()) {
                Optional<Nota> notaOpt = incidenciaRepository.findByIdNota(notaId);
                if (notaOpt.isPresent()) {
                    notas.add(notaOpt.get());
                } else {
                    throw new NotaNotFoundException("Nota no encontrada con ID: " + notaId);
                }
            }
        }
        return incidenciaRepository.save(incidencia);
    }


    public void deleteIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }


}

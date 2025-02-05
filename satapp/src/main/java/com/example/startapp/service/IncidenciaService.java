package com.example.startapp.service;

import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.GetIncidenciaDto;
import com.example.startapp.error.IncidenciaNotFoundException;
import com.example.startapp.error.NotaNotFoundException;
import com.example.startapp.model.*;
import com.example.startapp.repo.CategoriaRepository;
import com.example.startapp.repo.IncidenciaRepository;
import com.example.startapp.repo.UbicacionRepository;
import com.example.startapp.repo.UsuarioRepository;
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

    @org.springframework.transaction.annotation.Transactional(readOnly = true) // Esto evita problemas con FetchType.LAZY
    public List<GetIncidenciaDto> getAllIncidencias() {

        List <GetIncidenciaDto> incidencias = incidenciaRepository.findAll()
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
         Optional <Incidencia> incidencia =incidenciaRepository.findById(id);

        if (incidencia.isEmpty()) {
            throw new IncidenciaNotFoundException("No se ha encontrado una nota con ese id");
        } else {
            return GetIncidenciaDto.of(incidencia.get());
        }

    }


    public Incidencia abrirIncidencia(EditIncidenciaDto getIncidenciaDto) {

        // 1️⃣ Guardar Ubicación si es nueva
        Ubicacion ubicacion = getIncidenciaDto.ubicacion();
        if (ubicacion != null && ubicacion.getId() == null) {
            ubicacion = ubicacionRepository.save(ubicacion);
        }

        // 2️⃣ Guardar Usuario si es nuevo
        Usuario usuario = getIncidenciaDto.usuario();
        if (usuario != null && usuario.getId() == null) {
            usuario = usuarioRepository.save(usuario);
        }

        // 3️⃣ Asociar categorías correctamente
        List<Categoria> categorias = new ArrayList<>();
        if (getIncidenciaDto.categorias() != null) {
            for (Categoria c : getIncidenciaDto.categorias()) {
                if (c.getId() != null) {
                    // Si la categoría ya tiene un ID, buscamos en la BD para evitar el error
                    Categoria categoriaExistente = categoriaRepository.findById(c.getId())
                            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + c.getId()));
                    categorias.add(categoriaExistente);
                } else {
                    // Si no tiene ID, es nueva y la guardamos
                    categorias.add(categoriaRepository.save(c));
                }
            }
        }

        // 4️⃣ Crear la Incidencia sin notas
        Incidencia incidencia = Incidencia.builder()
                .fecha(getIncidenciaDto.fecha())
                .titulo(getIncidenciaDto.titulo())
                .descripcion(getIncidenciaDto.descripcion())
                .estado(getIncidenciaDto.estado())
                .urgencia(getIncidenciaDto.urgencia())
                .categorias(categorias) // Ahora sí, bien asociadas
                .ubicacion(ubicacion)
                .usuario(usuario)
                .build();

        // 5️⃣ Agregar las Notas con el helper
        if (getIncidenciaDto.notas() != null) {
            for (Nota nota : getIncidenciaDto.notas()) {
                if (nota != null) {
                    incidencia.addNota(nota);
                }
            }
        }

        // 6️⃣ Guardamos y retornamos la incidencia con sus asociaciones correctas
        return incidenciaRepository.save(incidencia);
    }


}

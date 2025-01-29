package com.example.startapp.service;

import com.example.startapp.dto.EditAlumnoDto;
import com.example.startapp.dto.EditUsuarioDTO;
import com.example.startapp.model.Alumno;
import com.example.startapp.repo.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;


    public List<Alumno> getAllAlumnos() {
        List<Alumno> result = alumnoRepository.findAll();

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron alumnos");
        } else {
            return result;
        }
    }

    public Alumno getAlumnoById(Long id) {
        Alumno result = alumnoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));
        return result;
    }

    public Alumno saveAlumno(EditAlumnoDto editAlumnoDto) {
        return alumnoRepository.save(Alumno.builder()
                .nombre(editAlumnoDto.nombre())
                .email(editAlumnoDto.email())
                .role(editAlumnoDto.role())
                .password(editAlumnoDto.password())
                .username(editAlumnoDto.username())
                .historicoCursos(editAlumnoDto.historicoCursos())
                .build());
    }


}

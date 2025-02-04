package com.example.startapp.service;

import com.example.startapp.dto.EditAlumnoDto;
import com.example.startapp.dto.EditHistoricoDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.error.AlumnoNotFoundException;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.HistoricoCursos;
import com.example.startapp.repo.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;


    @Transactional
    public List<Alumno> getAllAlumnos() {
        List<Alumno> result = alumnoRepository.findAllHis();

        if (result.isEmpty()) {
            throw new AlumnoNotFoundException("No se encontraron alumnos");
        } else {
            return result;
        }
    }

    public Alumno getAlumnoById(Long id) {
        Optional<Alumno> result = alumnoRepository.findByIdHis(id);

        if (result.isEmpty()) {
            throw new AlumnoNotFoundException("Alumno no encontrado");
        } else {
            return result.get();
        }

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


    public HistoricoCursos saveHistoricoCurso(Long alumnoId, EditHistoricoDto editHistoricoDto) {
        Alumno alumno = getAlumnoById(alumnoId);
        HistoricoCursos historicoCursos = HistoricoCursos.builder()
                .curso(editHistoricoDto.curso())
                .cursoEscolar(editHistoricoDto.cursoEscolar())
                .alumno(alumno)
                .build();
        alumno.addHistoricoCursos(historicoCursos);
        alumnoRepository.save(alumno);
        return historicoCursos;
    }


    public Alumno editAlumno(Long id, EditAlumnoDto editAlumnoDto) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);

        if (alumno.isPresent()) {
            alumno.map(a -> {
                a.setNombre(editAlumnoDto.nombre());
                a.setEmail(editAlumnoDto.email());
                a.setRole(editAlumnoDto.role());
                a.setPassword(editAlumnoDto.password());
                a.setUsername(editAlumnoDto.username());
                a.setHistoricoCursos(editAlumnoDto.historicoCursos());
                return alumnoRepository.save(a);
            });
        } else {
            throw new AlumnoNotFoundException("Alumno no encontrado");
        }

        return alumno.get();
    }

}

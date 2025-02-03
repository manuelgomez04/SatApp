package com.example.startapp.service;

import com.example.startapp.dto.EditAlumnoDto;
import com.example.startapp.dto.EditHistoricoDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.HistoricoCursos;
import com.example.startapp.repo.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;


    @Transactional
    public List<Alumno> getAllAlumnos() {
        List<Alumno> result = alumnoRepository.findAllHis();

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron alumnos");
        } else {
            return result;
        }
    }

    public Alumno getAlumnoById(Long id) {
        Alumno result = alumnoRepository
                .findByIdHis(id)
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



    public GetAlumnoDto editAlumno(Long id, EditAlumnoDto editAlumnoDto) {
        return alumnoRepository.findById(id)
                .map(alumno -> {
                    alumno.setNombre(editAlumnoDto.nombre());
                    alumno.setEmail(editAlumnoDto.email());
                    alumno.setRole(editAlumnoDto.role());
                    alumno.setPassword(editAlumnoDto.password());
                    alumno.setUsername(editAlumnoDto.username());
                    alumno.setHistoricoCursos(editAlumnoDto.historicoCursos());
                    return  GetAlumnoDto.of( alumnoRepository.save(alumno));
                })
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));
    }

}

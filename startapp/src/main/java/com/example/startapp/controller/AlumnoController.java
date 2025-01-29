package com.example.startapp.controller;

import com.example.startapp.dto.EditAlumnoDto;
import com.example.startapp.dto.EditUsuarioDTO;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetUsuarioDto;
import com.example.startapp.model.Alumno;
import com.example.startapp.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/alumno")
@RequiredArgsConstructor
@RestController
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public List<GetAlumnoDto> getAlumnos(){
        return alumnoService.getAllAlumnos().stream().map(GetAlumnoDto::of).toList();
    }

    @PostMapping
    public EditAlumnoDto saveAlumno(@RequestBody EditAlumnoDto alumnoNuevo){
        Alumno alumno = alumnoService.saveAlumno(alumnoNuevo);
        return GetUsuarioDto.of(alumno);
    }
}

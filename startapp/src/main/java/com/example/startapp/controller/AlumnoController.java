package com.example.startapp.controller;

import com.example.startapp.dto.*;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.HistoricoCursos;
import com.example.startapp.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/alumno")
@RequiredArgsConstructor
@RestController
@Tag(name = "Alumno", description = "Operaciones relacionadas con los alumnos")

public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Obtiene todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado alumnos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                               "nombre": "Juan PÃ©rez",
                                                               "username": "juanperez",
                                                               "email": "juan.perez@example.com",
                                                               "password": "password123",
                                                               "role": "USER",
                                                               "historicoCursos": []
                                                               },
                                             ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun alumno",
                    content = @Content),
    })
    @GetMapping
    public List<GetAlumnoDto> getAlumnos() {
        return alumnoService.getAllAlumnos().stream().map(GetAlumnoDto::of).toList();
    }

    @Operation(summary = "Crea un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un alumno",
                    content = @Content),
    })
    @PostMapping
    public GetAlumnoDto saveAlumno(@RequestBody EditAlumnoDto alumnoNuevo) {
        Alumno alumno = alumnoService.saveAlumno(alumnoNuevo);
        return GetAlumnoDto.of(alumno);
    }

    @Operation(summary = "Crea un nuevo historico de curso para un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el historico de curso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un historico de curso",
                    content = @Content),
    })
    @PostMapping("/{alumnoId}/historico")
    public GetHistoricoDto saveHistoricoCurso(@PathVariable Long alumnoId, @RequestBody EditHistoricoDto historicoDto){
        HistoricoCursos historicoCursos = alumnoService.saveHistoricoCurso(alumnoId, historicoDto);

        return GetHistoricoDto.of(historicoCursos);
    }
}

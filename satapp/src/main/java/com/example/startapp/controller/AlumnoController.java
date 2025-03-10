package com.example.startapp.controller;

import com.example.startapp.dto.*;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.HistoricoCursos;
import com.example.startapp.model.Ubicacion;
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
import org.springframework.http.ResponseEntity;
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
                                                    "historicoCursos": [
                                                        {
                                                            "curso": "2Âº ESO",
                                                            "cursoEscolar": "2020-2021"
                                                        },
                                                        {
                                                            "curso": "1Âº ESO",
                                                            "cursoEscolar": "2019-2020"
                                                        }
                                                    ]
                                                },
                                                {
                                                    "nombre": "Juan PÃ©rez",
                                                    "username": "juanperez",
                                                    "email": "juan.perez@example.com",
                                                    "password": "password123",
                                                    "role": "USER",
                                                    "historicoCursos": [
                                                        {
                                                            "curso": "1Âº ESO",
                                                            "cursoEscolar": "2019-2020"
                                                        }
                                                    ]
                                                }
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


    @Operation(summary = "Obtiene un alumno por su Id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el alumno buscado",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Juan PÃ©rez",
                                                "username": "juanperez",
                                                "email": "juan.perez@example.com",
                                                "password": "password123",
                                                "role": "USER",
                                                "historicoCursos": [
                                                    {
                                                        "curso": "1Âº ESO",
                                                        "cursoEscolar": "2019-2020"
                                                    },
                                                    {
                                                        "curso": "2Âº ESO",
                                                        "cursoEscolar": "2020-2021"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el alumno",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetAlumnoDto getAlumno(@PathVariable Long id) {
        return GetAlumnoDto.of(alumnoService.getAlumnoById(id));
    }

    @Operation(summary = "Crea un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el alumno",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un alumno",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetAlumnoDto> saveAlumno(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del alumno", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class),
                    examples = @ExampleObject(value = """
                                {
                                    "nombre": "Juan ",
                                    "username": "juanperez",
                                    "password": "password123",
                                    "email": "juan.perez@example.com",
                                    "role": "USER",
                                    "historicoCursos": [
                                        {\s
                                            "curso": "1º Bach",
                                            "cursoEscolar": "2023-2024"
                                        }
                                    ]
                                }
                            """))) @RequestBody EditAlumnoDto alumnoNuevo) {
        Alumno alumno = alumnoService.saveAlumno(alumnoNuevo);
        return ResponseEntity.ok(GetAlumnoDto.of(alumno));
    }

    @Operation(summary = "Crea un nuevo historico de curso para un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el historico de curso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un historico de curso",
                    content = @Content),
    })
    @PostMapping("/{alumnoId}/historico")
    public ResponseEntity<GetHistoricoDto> saveHistoricoCurso(@PathVariable Long alumnoId, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del historico del alumno", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HistoricoCursos.class),
                    examples = @ExampleObject(value = """
                               {
                                   "curso": "1º Bach",
                                   "cursoEscolar": "2023-2024"
                               }
                            """))) @RequestBody EditHistoricoDto historicoDto) {
        HistoricoCursos historicoCursos = alumnoService.saveHistoricoCurso(alumnoId, historicoDto);

        return ResponseEntity.ok(GetHistoricoDto.of(historicoCursos));
    }


    @Operation(summary = "Edita un alumno buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el alumno",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(


                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun alumno",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetAlumnoDto editAlumno(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del alumno", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class),
                    examples = @ExampleObject(value = """
                              {
                                  "nombre": "Juan Prez",
                                  "username": "juanperezzfaksdhfalkjshdfaslkhkhdz",
                                  "email": "juan.perez@example.com",
                                  "password": "password123456789",
                                  "role": "USER"
                              }
                            """))) @RequestBody EditAlumnoDto editAlumnoDto) {
        return GetAlumnoDto.of(alumnoService.editAlumno(id, editAlumnoDto));
    }
}

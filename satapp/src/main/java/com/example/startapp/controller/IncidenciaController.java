package com.example.startapp.controller;

import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetIncidenciaDto;
import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Usuario;
import com.example.startapp.service.IncidenciaService;
import com.example.startapp.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incidencia")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;
    private final TecnicoService tecnicoService;


    @Operation(summary = "Obtiene todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las incidencias",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            
                                                    {
                                                        "nombre": "Aula 1ºDAM"
                                                    },
                                                    {
                                                        "nombre": "Aula 2ºDAM"
                                                    }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningúna incidencia",
                    content = @Content),
    })
    @GetMapping
    public List<GetIncidenciaDto> getAll() {
        return incidenciaService.getAllIncidencias();
    }

    @Operation(summary = "Obtener una incidencia por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la incidencia",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                        "nombre": "Aula 1ºDAM"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetIncidenciaDto getById(@PathVariable Long id) {
        return incidenciaService.findById(id);
    }



    @Operation(summary = "Crea un nuevo historico de curso para un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el historico de curso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un historico de curso",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaDto> saveIncidencia(@RequestBody EditIncidenciaDto incidenciaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidenciaService.abrirIncidencia(incidenciaDto)));
    }

    @Operation(summary = "Borra un usuario buscado por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncidencia(@PathVariable Long id) {
        incidenciaService.deleteIncidencia(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

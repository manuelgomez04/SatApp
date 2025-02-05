package com.example.startapp.controller;

import com.example.startapp.dto.*;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.Tecnico;
import com.example.startapp.model.Ubicacion;
import com.example.startapp.service.TecnicoService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/tecnico")
@Tag(name = "Técnico", description = "Operaciones relacionadas con los técnicos")

public class TecnicoController {

    private final TecnicoService tecnicoService;

    @Operation(summary = "Obtiene todos los técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado técnicos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                          "nombre": "MarÃ­a ",
                                                          "username": "marialopez",
                                                          "email": "maria.lopez@example.com",
                                                          "password": "password456",
                                                          "role": "ADMIN"
                                                      },
                                                      {
                                                          "nombre": "MarÃ­a aaaa",
                                                          "username": "marialopez",
                                                          "email": "maria.lopez@example.com",
                                                          "password": "password456",
                                                          "role": "ADMIN"
                                                      }
                                             ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun técnico",
                    content = @Content),
    })
    @GetMapping
    public List<GetTecnicoDto> getTecnicos() {
        return tecnicoService.getAllTecnicos().stream().map(GetTecnicoDto::of).toList();
    }


    @Operation(summary = "Obtiene un técnico por su Id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el técnico buscado",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            
                                                 {
                                                    "nombre": "Juan PÃ©rez",
                                                    "username": "juanperez",
                                                    "email": "juan.perez@example.com",
                                                    "password": "password123",
                                                    "role": "USER"
                                                 },
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el alumno",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetTecnicoDto getTecnico(@PathVariable Long id) {
        return GetTecnicoDto.of(tecnicoService.getTecnicoById(id));
    }

    @Operation(summary = "Crea un nuevo técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el técnico",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un técnico",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetTecnicoDto> saveTecnico(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del tecnico", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tecnico.class),
                    examples = @ExampleObject(value = """
                            {
                                   "nombre": "MarÃ­a ",
                                   "username": "marialopez",
                                   "email": "maria.lopez@example.com",
                                   "password": "password456",
                                   "role": "ADMIN"
                               }
                            """))) @RequestBody EditTecnicoDto nuevoTecnico) {
        Tecnico tecnico = tecnicoService.saveTecnico(nuevoTecnico);
        return ResponseEntity.ok(GetTecnicoDto.of(tecnico));
    }

    @Operation(summary = "Edita un técnico buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el técnico",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun técnico",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetTecnicoDto editTecnico(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del tecnico", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Tecnico.class),
                    examples = @ExampleObject(value = """
                            {
                                    "nombre": "Mar­ado ",
                                    "username": "marialopez",
                                    "email": "maria.lopez@example.com",
                                    "password": "password456",
                                    "role": "ADMIN"
                                }
                            """))) @RequestBody EditTecnicoDto editTecnicoDto) {
        return GetTecnicoDto.of(tecnicoService.editTecnico(id, editTecnicoDto));
    }

    @PutMapping("/{idTecnico}/asignar/{idIncidencia}")
    public ResponseEntity<GetTecnicoDto> asignarIncidencia(@PathVariable Long idTecnico, @PathVariable Long idIncidencia) {
        return ResponseEntity.ok(GetTecnicoDto.of(tecnicoService.addIncidencia(idTecnico, idIncidencia)));
    }


    @PutMapping("/gestionar-incidencia/{id}")
    public GetIncidenciaDto gestionarIncidencia(@PathVariable Long id, @RequestBody EditIncidenciaDto editIncidenciaDto) {
        return GetIncidenciaDto.of(tecnicoService.gestionarIncidencia(id, editIncidenciaDto));
    }

}

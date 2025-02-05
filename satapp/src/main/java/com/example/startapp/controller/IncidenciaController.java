package com.example.startapp.controller;

import com.example.startapp.dto.EditIncidenciaDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetIncidenciaDto;
import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.model.Incidencia;
import com.example.startapp.model.Nota;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incidencia")
@Tag(name = "Incidencia", description = "Operaciones relacionadas con incidencias")
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
                                                       "fecha": "1943-02-10",
                                                       "titulo": "Rotura",
                                                       "descripcion": "Se ha roto algo no se sabe el que",
                                                       "estado": "ABIERTA",
                                                       "urgencia": true,
                                                       "categorias": [],
                                                       "equipos": [],
                                                       "notas": [],
                                                       "ubicacion": {
                                                           "nombre": "Aula 1ÂºComercio"
                                                       },
                                                       "usuario": null
                                                   },
                                                   {
                                                       "fecha": "2024-02-10",
                                                       "titulo": "Electricidad",
                                                       "descripcion": "Falla el cuadro electrico",
                                                       "estado": "PENDIENTE",
                                                       "urgencia": false,
                                                       "categorias": [],
                                                       "equipos": [
                                                           {
                                                               "nombre": "Pc2",
                                                               "caracteristicas": "Car1,Car2,Car3",
                                                               "ubicacionId": 151
                                                           },
                                                           {
                                                               "nombre": "Pc3",
                                                               "caracteristicas": "Car1,Car2,Car3",
                                                               "ubicacionId": 151
                                                           }
                                                       ],
                                                       "notas": [],
                                                       "ubicacion": {
                                                           "nombre": "Aula 2ÂºDAM"
                                                       },
                                                       "usuario": {
                                                           "nombre": "Juan PÃ©rez",
                                                           "username": "juanperez",
                                                           "email": "juan.perez@example.com",
                                                           "password": "password123",
                                                           "role": "USER"
                                                       }
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
                                                "fecha": "2024-02-10",
                                                "titulo": "Electricidad",
                                                "descripcion": "Falla el cuadro electrico",
                                                "estado": "PENDIENTE",
                                                "urgencia": false,
                                                "categorias": [],
                                                "equipos": [
                                                    {
                                                        "nombre": "Pc2",
                                                        "caracteristicas": "Car1,Car2,Car3",
                                                        "ubicacionId": 151
                                                    },
                                                    {
                                                        "nombre": "Pc3",
                                                        "caracteristicas": "Car1,Car2,Car3",
                                                        "ubicacionId": 151
                                                    }
                                                ],
                                                "notas": [],
                                                "ubicacion": {
                                                    "nombre": "Aula 2ÂºDAM"
                                                },
                                                "usuario": {
                                                    "nombre": "Juan PÃ©rez",
                                                    "username": "juanperez",
                                                    "email": "juan.perez@example.com",
                                                    "password": "password123",
                                                    "role": "USER"
                                                }
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


    @Operation(summary = "Crea una nueva incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la incidencia",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado la incidencia",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaDto> saveIncidencia(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la incidencia", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidencia.class),
                    examples = @ExampleObject(value = """
                            {
                                "fecha": "1943-02-10",
                                "titulo": "Rotura",
                                "descripcion": "Se ha roto algo no se sabe el que",
                                "estado": "ABIERTA",
                                "urgencia": true,
                                "categorias": [
                                    101
                                ],
                                "equipos": [
                                    102
                                ],
                                "notas": [],
                                "ubicacion": {
                                    "id": 1
                                },
                                "usuario": null
                            }
                            """))) @RequestBody EditIncidenciaDto incidenciaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidenciaService.abrirIncidencia(incidenciaDto)));
    }

    @Operation(summary = "Borra una incidencia buscando por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado la incidencia",
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

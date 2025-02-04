package com.example.startapp.controller;

import com.example.startapp.dto.*;
import com.example.startapp.model.Equipo;
import com.example.startapp.model.Ubicacion;
import com.example.startapp.service.EquipoService;
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
@RequestMapping("/equipo")
@Tag(name = "Equipo", description = "Controlador de equipo")
public class EquipoController {


    private final EquipoService equipoService;

    @Operation(summary = "Obtiene todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los equipos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                              {
                                                     "nombre": "Pc1",
                                                     "caracteristicas": "Car1,Car2,Car3"
                                                 },
                                                 {
                                                     "nombre": "Televisión",
                                                     "caracteristicas": "Car2,Car3"
                                                 }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún equipo",
                    content = @Content),
    })
    @GetMapping
    public List<GetEquipoDto> getAll() {
        return equipoService.findAll().stream().map(GetEquipoDto::of).toList();
    }

    @Operation(summary = "Obtener un equipo por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el equipo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                               "nombre": "Pc1",
                                               "caracteristicas": "Car1,Car2,Car3"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el equipo",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetEquipoDto getById(@PathVariable Long id) {
        return GetEquipoDto.of(equipoService.findById(id));
    }

    @Operation(summary = "Crear un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el equipo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un equipo",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetEquipoDto> saveEquipo(@RequestBody GetEquipoDto equipoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoDto.of(equipoService.saveEquipo(equipoDto)));
    }

    @Operation(summary = "Edita un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el equipo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EditEquipoDto.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún equipo",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetEquipoDto editEquipo(@PathVariable Long id, @RequestBody EditEquipoDto equipoDto) {
        return GetEquipoDto.of(equipoService.editEquipo(id, equipoDto));
    }


    @Operation(summary = "Borra un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el equipo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Equipo.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún equipo",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }
}

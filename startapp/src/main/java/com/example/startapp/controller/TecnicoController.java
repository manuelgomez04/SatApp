package com.example.startapp.controller;

import com.example.startapp.dto.EditTecnicoDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetTecnicoDto;
import com.example.startapp.model.Tecnico;
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

    @Operation(summary = "Crea un nuevo técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el técnico",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un técnico",
                    content = @Content),
    })
    @PostMapping
    public GetTecnicoDto saveTecnico(@RequestBody EditTecnicoDto nuevoTecnico) {
        Tecnico tecnico = tecnicoService.saveTecnico(nuevoTecnico);
        return GetTecnicoDto.of(tecnico);
    }
}

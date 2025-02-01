package com.example.startapp.controller;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.dto.GetNotaDto;
import com.example.startapp.model.Nota;
import com.example.startapp.service.NotaService;
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
@RequestMapping("/nota")
@Tag(name = "Nota", description = "Controlador de notas")
public class NotasController {

    private final NotaService notaService;

    @Operation(summary = "Obtiene todas las notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las notas",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "incidenciaId": 51,
                                                    "fecha": "2025-01-29",
                                                    "contenido": "Nota1",
                                                    "autor": "Jose"
                                                },
                                                {
                                                    "incidenciaId": 51,
                                                    "fecha": "2025-01-29",
                                                    "contenido": "Nota2",
                                                    "autor": "Pepe"
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningúna nota",
                    content = @Content),
    })
    @GetMapping("/")
    public List<GetNotaDto> getAll() {
        return notaService.findAll().stream().map(GetNotaDto::of).toList();
    }

    @Operation(summary = "Obtener una nota por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la nota",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "incidenciaId": 51,
                                                "fecha": "2025-01-29",
                                                "contenido": "Nota1",
                                                "autor": "Jose"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la nota",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetNotaDto getNotaById(@PathVariable Long id) {
        return GetNotaDto.of(notaService.findNotaById(id));
    }


    @Operation(summary = "Crea una nota por el id de una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la nota",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EditNotaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la nota",
                    content = @Content),
    })
    @PostMapping("/{incidenciaId}")
    public ResponseEntity<Nota> saveNota(@PathVariable Long incidenciaId, @RequestBody EditNotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.saveNota(incidenciaId, nota));
    }


    @Operation(summary = "Edita una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la nota",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EditNotaDto.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento",
                    content = @Content),
    })
    @PutMapping("/{notaId}")
    public Nota editNota(@PathVariable Long notaId, @RequestBody EditNotaDto nota) {
        return notaService.editNota(notaId, nota);
    }


    @Operation(summary = "Borra una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado la nota",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Nota.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningúna nota",
                    content = @Content),
    })
    @DeleteMapping("/{notaId}/{incidenciaId}")
    public ResponseEntity<?> deleteNota(@PathVariable Long notaId, @PathVariable Long incidenciaId) {
        notaService.removeNota(notaId, incidenciaId);
        return ResponseEntity.noContent().build();
    }
}

package com.example.startapp.controller;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.dto.GetNotaDto;
import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.model.Nota;
import com.example.startapp.model.Ubicacion;
import com.example.startapp.service.UbicacionService;
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
@RequestMapping("/ubicacion")
@Tag(name = "Ubicacion", description = "Controlador para ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Obtiene todas las ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las ubicaciones",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
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
                    description = "No se ha encontrado ningúna ubicación",
                    content = @Content),
    })
    @GetMapping
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAll().stream().map(GetUbicacionDto::of).toList();
    }

    @Operation(summary = "Obtener una ubicación por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la ubicación",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                        "nombre": "Aula 1ºDAM"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicación",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetUbicacionDto getById(@PathVariable Long id) {
        return GetUbicacionDto.of(ubicacionService.findById(id).get());
    }

    @Operation(summary = "Crea una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la ubicación",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la ubicación",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetUbicacionDto> saveUbicacion(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la ubicación", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Ubicacion.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre":"Ubi2"
                            }
                            """))) @RequestBody GetUbicacionDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.saveUbicacion(nuevo)));
    }

    @Operation(summary = "Borra una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado la ubicación",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ubicacion.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningúna ubicación",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }
}

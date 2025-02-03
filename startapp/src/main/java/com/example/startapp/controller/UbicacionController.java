package com.example.startapp.controller;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.dto.GetUbicacionDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubicacion")
@Tag(name = "Ubicacion",description = "Controlador para ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Crea una ubicacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la ubicacion",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la ubicacion",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetUbicacionDto> saveUbicacion(@RequestBody GetUbicacionDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.saveUbicacion(nuevo)));
    }
}

package com.example.startapp.controller;

import com.example.startapp.dto.EditPersonalDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetPersonalDto;
import com.example.startapp.dto.GetTecnicoDto;
import com.example.startapp.model.Personal;
import com.example.startapp.service.PersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "Operaciones relacionadas con el personal")
public class PersonalController {

    private final PersonalService personalService;

    @Operation(summary = "Obtiene todos los miembros del personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado personal",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
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
                    description = "No se ha encontrado ningun miembro del personal",
                    content = @Content),
    })
    @GetMapping
    public List<GetPersonalDto> getPersonal() {
        return personalService.getAllPersonal().stream().map(GetPersonalDto::of).toList();
    }

    @Operation(summary = "Crea un miembro del personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el miembro del personal",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un miembro del personal",
                    content = @Content),
    })
    @PostMapping
    public GetPersonalDto savePersonal(@RequestBody EditPersonalDto nuevoPersonal) {
        Personal personal = personalService.savePersonal(nuevoPersonal);
        return GetPersonalDto.of(personal);
    }
}

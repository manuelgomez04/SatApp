package com.example.startapp.controller;

import com.example.startapp.dto.EditUsuarioDto;
import com.example.startapp.dto.GetUsuarioDto;
import com.example.startapp.model.Usuario;
import com.example.startapp.service.AlumnoService;
import com.example.startapp.service.UsuarioService;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "Operaciones relacionadas con los usuarios em general, no para un tipo concreto")

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AlumnoService alumnoService;


    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado usuarios",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "nombre": "Juan PÃ©rez",
                                                      "username": "juanperez",
                                                      "email": "juan.perez@example.com",
                                                      "password": "password123",
                                                      "role": "USER"
                                                  },
                                                  {
                                                      "nombre": "MarÃ­a ",
                                                      "username": "marialopez",
                                                      "email": "maria.lopez@example.com",
                                                      "password": "password456",
                                                      "role": "ADMIN"
                                                  }
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun usuario",
                    content = @Content),
    })
    @GetMapping
    public List<GetUsuarioDto> getUsuario() {
        return usuarioService.getAllUsuarios().stream().map(GetUsuarioDto::of).toList();
    }

    @Operation(summary = "Borra un usuario buscado por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

        usuarioService.deleteUsuario(id);

        return ResponseEntity.noContent().build();
    }

}

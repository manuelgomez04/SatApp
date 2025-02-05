package com.example.startapp.controller;

import com.example.startapp.dto.EditCategoriaDto;
import com.example.startapp.dto.GetAlumnoDto;
import com.example.startapp.dto.GetCategoriaDto;
import com.example.startapp.model.Alumno;
import com.example.startapp.model.Categoria;
import com.example.startapp.service.CategoriaService;
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
@RequestMapping("/categoria")
@Tag(name = "Categoría", description = "Operaciones relacionadas con las categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @Operation(summary = "Obtiene todos los categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la categoría",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                        "nombre": "CATEGORIA1",
                                                        "subCategorias": [
                                                            {
                                                                "nombre": "CATEGORIA2"
                                                            }
                                                        ],
                                                        "categoriaPadre": null
                                                    },
                                                    {
                                                        "nombre": "CATEGORIA4",
                                                        "subCategorias": [],
                                                        "categoriaPadre": null
                                                    },
                                                    {
                                                        "nombre": "CATEGORIA3",
                                                        "subCategorias": [],
                                                        "categoriaPadre": null
                                                    },
                                                    {
                                                        "nombre": "CATEGORIA2",
                                                        "subCategorias": [],
                                                        "categoriaPadre": {
                                                            "nombre": "CATEGORIA1"
                                                        }
                                                    }
                                                ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría",
                    content = @Content),
    })
    @GetMapping
    public List<GetCategoriaDto> getCategorias() {
        return categoriaService.getAllCategorias().stream().map(GetCategoriaDto::of).toList();
    }

    @Operation(summary = "Obtiene una categoría por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoría",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "CATEGORIA2",
                                                "subCategorias": [],
                                                "categoriaPadre": {
                                                    "nombre": "CATEGORIA1"
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetCategoriaDto> getCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(GetCategoriaDto.of(categoriaService.getCategoriaById(id)));
    }


    @Operation(summary = "Crea una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la categoría",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado una categoría",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetCategoriaDto> saveCategoria(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la categoría", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Categoria.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "CATEGORIA035",
                                "subCategorias": [],
                                "categoriaPadre": {}
                            }
                            """))) @RequestBody EditCategoriaDto categoria) {
        return ResponseEntity.ok(GetCategoriaDto.of(categoriaService.saveCategoria(categoria)));
    }

    @Operation(summary = "Edita una categoría buscada por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la categoría",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetCategoriaDto editCategoria(@PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo de la categoría", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Categoria.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "CATEGORIA03405054215",
                                "subCategorias": [
                                    {
                                        "id": 101
                                    }
                                ],
                                "categoriaPadre": {
                                    "id": 51
                                }
                            }
                            """))) @RequestBody EditCategoriaDto editCategoriaDto) {
        return GetCategoriaDto.of(categoriaService.editCategoria(id, editCategoriaDto));
    }


}

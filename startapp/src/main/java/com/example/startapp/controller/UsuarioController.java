package com.example.startapp.controller;

import com.example.startapp.dto.EditUsuarioDTO;
import com.example.startapp.dto.GetUsuarioDto;
import com.example.startapp.model.Usuario;
import com.example.startapp.service.AlumnoService;
import com.example.startapp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AlumnoService alumnoService;


    @GetMapping
    public List<GetUsuarioDto> getUsuario() {
        return usuarioService.getAllUsuarios().stream().map(GetUsuarioDto::of).toList();
    }



    @PostMapping
    public GetUsuarioDto saveUsuario(@RequestBody EditUsuarioDTO nuevoUsuario) {
        Usuario usuario = usuarioService.saveUsuario(nuevoUsuario);
        return GetUsuarioDto.of(usuario);
    }





}

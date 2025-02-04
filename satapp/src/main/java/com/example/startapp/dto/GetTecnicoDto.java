package com.example.startapp.dto;

import com.example.startapp.model.Tecnico;
import com.example.startapp.model.Usuario;

public record GetTecnicoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {
    public static GetTecnicoDto of(Tecnico tecnico){
        return new GetTecnicoDto(
                tecnico.getNombre(),
                tecnico.getUsername(),
                tecnico.getEmail(),
                tecnico.getPassword(),
                tecnico.getRole()
        );
    }
}

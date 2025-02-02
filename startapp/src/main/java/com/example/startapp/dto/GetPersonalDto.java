package com.example.startapp.dto;

import com.example.startapp.model.Personal;
import com.example.startapp.model.Tecnico;

public record GetPersonalDto(
        String nombre,
        String username,
        String email,
        String password,
        String role
) {
    public static GetPersonalDto of(Personal personal){
        return new GetPersonalDto(
                personal.getNombre(),
                personal.getUsername(),
                personal.getEmail(),
                personal.getPassword(),
                personal.getRole()
        );
    }
}

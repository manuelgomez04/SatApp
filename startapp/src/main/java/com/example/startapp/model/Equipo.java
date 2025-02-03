package com.example.startapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private List<String> caracteristicas;
    
}

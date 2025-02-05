package com.example.startapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Entity
public class Tecnico extends Usuario {



    @ManyToMany
    @ToString.Exclude
    private Set<Incidencia> gestionarIncidencias = new HashSet<>();


}

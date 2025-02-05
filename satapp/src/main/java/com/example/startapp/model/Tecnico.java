package com.example.startapp.model;

import jakarta.persistence.*;
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
    @JoinTable( foreignKey = @ForeignKey(name = "FK_tecnico_incidencia"))
    @ToString.Exclude
    private Set<Incidencia> gestionarIncidencias = new HashSet<>();

    public void addIncidencia(Incidencia incidencia) {
        gestionarIncidencias.add(incidencia);
    }

    public void removeIncidencia(Incidencia incidencia) {
        gestionarIncidencias.remove(incidencia);
    }


}

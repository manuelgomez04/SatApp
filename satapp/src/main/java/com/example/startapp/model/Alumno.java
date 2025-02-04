package com.example.startapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alumno extends Usuario{


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (mappedBy = "alumno",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<HistoricoCursos> historicoCursos;

    public void addHistoricoCursos(HistoricoCursos historicoCursos){
        this.historicoCursos.add(historicoCursos);
        historicoCursos.setAlumno(this);
    }

    public void removeHistoricoCursos(HistoricoCursos historicoCursos){
        this.historicoCursos.remove(historicoCursos);
        historicoCursos.setAlumno(null);
    }
}

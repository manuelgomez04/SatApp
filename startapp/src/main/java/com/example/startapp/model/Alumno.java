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
    fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<HistoricoCursos> historicoCursos;

}

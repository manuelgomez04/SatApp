package com.example.startapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoCursos {

    @Id
    @GeneratedValue
    private Long cursoEscolar;

    private String curso;

    @ManyToOne
    private Alumno alumno;


}

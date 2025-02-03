package com.example.startapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HistoricoCursos {

    @Id
    @GeneratedValue
    private Long id;

    private String cursoEscolar;

    private String curso;

    @ManyToOne
    private Alumno alumno;

    public HistoricoCursos(String curso, String cursoEscolar) {
        this.curso = curso;
        this.cursoEscolar = cursoEscolar;
    }

}

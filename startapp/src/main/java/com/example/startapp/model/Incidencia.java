package com.example.startapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Incidencia {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime fecha;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Boolean urgencia;

    /*
    Nota, ubicacion,equipo, categoria, usuario, tecnico
     */

}

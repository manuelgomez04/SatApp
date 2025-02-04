package com.example.startapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    @Column(length = 400)
    private String caracteristicas;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", foreignKey = @ForeignKey(name = "fk_equipo_ubicacion"))
    private Ubicacion ubicacion;
}

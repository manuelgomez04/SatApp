package com.example.startapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Ubicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

}

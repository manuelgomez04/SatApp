package com.example.startapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private LocalDate fecha;
    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Boolean urgencia;


    //Asociacion nota
    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "incidencia",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    //Helpers nota

    public void addNota(Nota nota) {
        notas.add(nota);
        nota.setIncidencia(this);
    }

    public void removeNota(Nota nota) {
        notas.remove(nota);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Incidencia that = (Incidencia) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

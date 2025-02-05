package com.example.startapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SQLDelete(sql = "UPDATE incidencia SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedIncidenciaService", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Categoria> categorias = new ArrayList<>();


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Equipo> equipos = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "incidencia",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    @ManyToMany(mappedBy = "gestionarIncidencias", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private Set<Tecnico> tecnicos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", foreignKey = @ForeignKey(name = "fk_incidencia_ubicacion"))
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_incidencia_usuario"))
    private Usuario usuario;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    //Helpers nota
    public void addNota(Nota nota) {
        notas.add(nota);
        nota.setIncidencia(this);
    }

    public void removeNota(Nota nota) {
        notas.remove(nota);

    }

    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);

    }

    public void removeCategoria(Categoria categoria) {
        categorias.remove(categoria);
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

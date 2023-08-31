package mx.edu.utez.platillos.modelo.platillo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.categoria.Categoria;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Table(name = "platillos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_platillo;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;
    @Column(nullable = true, columnDefinition = "datetime default now()")
    private String fecha_creacion;
    @Column(nullable = true, columnDefinition = "datetime default now()")
    private String fecha_modificacion;
    @Column(nullable = false, columnDefinition = "decimal(10,2) default 0.00")
    private Double precio;
    @Column(nullable = false, length = 100)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;
}

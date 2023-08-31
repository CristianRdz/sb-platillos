package mx.edu.utez.platillos.modelo.ordenesPlatillo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.orden.Orden;
import mx.edu.utez.platillos.modelo.platillo.Platillo;
import mx.edu.utez.platillos.modelo.tamanio.Tamanio;

import javax.persistence.*;

@Entity
@Table(name = "ordenes_platillos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdenPlatillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_elemento;
    @Column(nullable = true, columnDefinition = "text")
    private String notas;
    @ManyToOne
    @JoinColumn(name = "fk_platillo")
    private Platillo platillo;
    @ManyToOne
    @JoinColumn(name = "fk_tamanio")
    private Tamanio tamanio;
    @ManyToOne
    @JoinColumn(name = "fk_orden")
    private Orden orden;

}

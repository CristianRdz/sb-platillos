package mx.edu.utez.platillos.modelo.tamanio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tamanios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tamanio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tamanio;

    @Column(nullable = false, length = 100)
    private String nombre;
}

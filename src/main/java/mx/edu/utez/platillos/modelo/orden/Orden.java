package mx.edu.utez.platillos.modelo.orden;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.rol.Rol;
import mx.edu.utez.platillos.modelo.usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "ordenes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orden;

    @Column(nullable = true, columnDefinition = "datetime default now()")
    private String fecha_creacion;


    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}

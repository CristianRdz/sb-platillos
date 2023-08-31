package mx.edu.utez.platillos.modelo.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.rol.Rol;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String primer_apellido;

    @Column(nullable = false, length = 50)
    private String segundo_apellido;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 200)
    private String contrasena;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;

    @ManyToOne
    @JoinColumn(name = "fk_rol")
    private Rol rol;


}

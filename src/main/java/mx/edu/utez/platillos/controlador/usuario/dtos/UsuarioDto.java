package mx.edu.utez.platillos.controlador.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.rol.Rol;
import mx.edu.utez.platillos.modelo.usuario.Usuario;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDto {
    private Long id_usuario;

    private String nombre;

    private String primer_apellido;

    private String segundo_apellido;

    private String correo;

    private String contrasena;

    private Boolean estatus;

    private Rol rol;

    public Usuario getUsuario(){
        return new Usuario( getId_usuario(),getNombre(),getPrimer_apellido(),getSegundo_apellido(),getCorreo(),getContrasena(),getEstatus(),getRol());
    }

}

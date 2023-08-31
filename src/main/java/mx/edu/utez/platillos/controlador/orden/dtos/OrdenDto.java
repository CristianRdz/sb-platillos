package mx.edu.utez.platillos.controlador.orden.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.modelo.orden.Orden;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrdenDto {
    private Long id_orden;
    private String fecha_creacion;
    private Boolean estatus;
    private Usuario usuario;
    public Orden getOrden(){
        return new Orden(getId_orden(),getFecha_creacion(),getEstatus(),getUsuario());
    }

}

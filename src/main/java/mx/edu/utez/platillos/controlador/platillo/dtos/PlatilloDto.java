package mx.edu.utez.platillos.controlador.platillo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.categoria.Categoria;
import mx.edu.utez.platillos.modelo.platillo.Platillo;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlatilloDto {
    private Long id_platillo;
    private String nombre;
    private Boolean estatus;
    private String fecha_creacion;
    private String fecha_modificacion;
    private Double precio;
    private String descripcion;
    private Categoria categoria;
    public Platillo getPlatillo(){
        return new Platillo(getId_platillo(),getNombre(),getEstatus(),getFecha_creacion(),getFecha_modificacion(),getPrecio(),getDescripcion(),getCategoria());
    }
}

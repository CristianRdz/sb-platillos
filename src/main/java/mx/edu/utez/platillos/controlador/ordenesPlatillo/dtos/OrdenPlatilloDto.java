package mx.edu.utez.platillos.controlador.ordenesPlatillo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.platillos.modelo.orden.Orden;
import mx.edu.utez.platillos.modelo.ordenesPlatillo.OrdenPlatillo;
import mx.edu.utez.platillos.modelo.platillo.Platillo;
import mx.edu.utez.platillos.modelo.tamanio.Tamanio;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrdenPlatilloDto {

    private Long id_elemento;
    private String notas;
    private Platillo platillo;
    private Tamanio tamanio;
    private Orden orden;

    public OrdenPlatillo getOrdenPlatillo(){
        return new OrdenPlatillo(getId_elemento(),getNotas(),getPlatillo(),getTamanio(),getOrden());
    }

}

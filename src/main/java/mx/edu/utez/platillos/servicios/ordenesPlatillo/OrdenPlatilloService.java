package mx.edu.utez.platillos.servicios.ordenesPlatillo;

import mx.edu.utez.platillos.modelo.orden.Orden;
import mx.edu.utez.platillos.modelo.ordenesPlatillo.OrdenPlatillo;
import mx.edu.utez.platillos.modelo.ordenesPlatillo.OrdenPlatilloRepository;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class OrdenPlatilloService {
    @Autowired
    private OrdenPlatilloRepository repository;

    @Transactional(readOnly = true)
    public Response<List<OrdenPlatillo>> getAll() {
        return new Response<>(this.repository.getAll(), false, 200, "Correcto");
    }
    @Transactional(readOnly = true)
    public Response<List<OrdenPlatillo>> getAllByOrden(Long id) {
        return new Response<>(this.repository.findByIdOrden(id), false, 200, "Correcto");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<OrdenPlatillo> insert(OrdenPlatillo OrdenPlatillo) {
        try {
            return new Response<>(this.repository.save(OrdenPlatillo), false, 200, "OrdenPlatillo registrado correctamente");
        } catch (Exception e) {
            return new Response<>(null, true, 400, "No se pudo registrar el OrdenPlatillo");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<OrdenPlatillo> update(OrdenPlatillo OrdenPlatillo) {
        if (this.repository.findByIdOrdenPlatillo(OrdenPlatillo.getId_elemento()) != null) {
            this.repository.saveAndFlush(OrdenPlatillo);
            return new Response<>(OrdenPlatillo, false, 200, "Orden actualizado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo actualizar el Orden");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<OrdenPlatillo> delete(Long id) {
        OrdenPlatillo Orden = this.repository.findByIdOrdenPlatillo(id);
        if (Orden != null) {
            this.repository.delete(Orden);
            return new Response<>(Orden, false, 200, "Orden eliminado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo eliminar el Orden");
        }
    }

    @Transactional(readOnly = true)
    public Response<OrdenPlatillo> getOne(Long id) {
        OrdenPlatillo ordenPlatillo = this.repository.findByIdOrdenPlatillo(id);
        if (ordenPlatillo != null) {
            return new Response<>(ordenPlatillo, false, 200, "Correcto");
        } else {
            return new Response<>(null, true, 400, "No se encontro el Orden");
        }
    }



}

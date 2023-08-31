package mx.edu.utez.platillos.servicios.orden;
import mx.edu.utez.platillos.modelo.orden.Orden;
import mx.edu.utez.platillos.modelo.orden.OrdenRepository;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class OrdenService {
    @Autowired
    private OrdenRepository repository;
    @Transactional(readOnly = true)
    public Response<List<Orden>> getAll() {
        return new Response<>(this.repository.findAll(), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Orden>> getAllByEstatus(Boolean estatus) {
        return new Response<>(this.repository.findByEstatus(estatus), false, 200, "Correcto"
        );
    }


    @Transactional(readOnly = true)
    public Response<List<Orden>> getAllByUsuario(Long id) {
        return new Response<>(this.repository.findByIdUsuario(id), false, 200, "Correcto"
        );
    }
    @Transactional(readOnly = true)
    public Response<Orden> getOneByUsuario(Long id) {
        return new Response<>(this.repository.findByIdUsuarioAndEstatus(id), false, 200, "Correcto"
        );
    }
    @Transactional(readOnly = true)
    public Response<String> getGananciasTotales() {
        return new Response<>(this.repository.gananciasTotales(), false, 200, "Correcto"
        );
    }
    @Transactional(readOnly = true)
    public Response<String> getGananciasRango(String fechaInicio, String fechaFin) {
        return new Response<>(this.repository.gananciasTotalesByFecha(fechaInicio,fechaFin), false, 200, "Correcto"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Orden> insert(Orden Orden) {
        if (this.repository.findByIdOrden(Orden.getId_orden()) == null) {
            return new Response<>(this.repository.save(Orden), false, 200, "Orden registrado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo registrar el Orden");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Orden> update(Orden Orden) {
        if (this.repository.findByIdOrdenAndEstatus(Orden.getId_orden(), true) != null) {
            this.repository.saveAndFlush(Orden);
            return new Response<>(Orden, false, 200, "Orden actualizado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo actualizar el Orden");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Orden> delete(Long id) {
        Orden Orden = this.repository.findByIdOrdenAndEstatus(id, true) ;
        if (Orden != null) {
            Orden.setEstatus(false);
            this.repository.saveAndFlush(Orden);
            return new Response<>(Orden, false, 200, "Orden eliminado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo eliminar el Orden");
        }
    }
    @Transactional(readOnly = true)
    public Response<Orden> getOne(Long id) {
        if (this.repository.findByIdOrden(id) != null) {
            Orden Orden = this.repository.findByIdOrden(id);
            if (Orden != null) {
                return new Response<>(Orden, false, 200, "Correcto");
            } else {
                return new Response<>(null, true, 400, "No se encontro el Orden");
            }
        } else {
            return new Response<>(null, true, 400, "No se encontro el Orden");
        }
    }
}

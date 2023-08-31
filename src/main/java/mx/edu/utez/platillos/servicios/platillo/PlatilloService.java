package mx.edu.utez.platillos.servicios.platillo;

import mx.edu.utez.platillos.modelo.platillo.Platillo;
import mx.edu.utez.platillos.modelo.platillo.PlatilloRepository;
import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class PlatilloService {
    @Autowired
    private PlatilloRepository repository;
    @Transactional(readOnly = true)
    public Response<List<Platillo>> getAll() {
        return new Response<>(this.repository.findAll(), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Platillo>> getAllByEstatus(Boolean estatus) {
        return new Response<>(this.repository.findByEstatus(estatus), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Platillo>> getAllByNombre(String nombre) {
        return new Response<>(this.repository.findByNombreContaining(nombre), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Platillo>> getAllByNombreAndEstatus(String nombre, Boolean estatus) {
        return new Response<>(this.repository.findByEstatusAndNombreContaining(estatus, nombre), false, 200, "Correcto");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Platillo> insert(Platillo platillo) {
        if (this.repository.findByNombre(platillo.getNombre()) == null) {
            return new Response<>(this.repository.save(platillo), false, 200, "Platillo registrado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo registrar el platillo");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Platillo> update(Platillo platillo) {
        if (this.repository.findByIdPlatillo(platillo.getId_platillo()) != null) {
            this.repository.saveAndFlush(platillo);
            return new Response<>(platillo, false, 200, "Platillo actualizado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo actualizar el platillo");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Platillo> delete(Long id) {
        Platillo platillo = this.repository.findByIdPlatillo(id);
        if (platillo != null) {
            platillo.setEstatus(!platillo.getEstatus());
            this.repository.saveAndFlush(platillo);
            return new Response<>(platillo, false, 200, "Platillo eliminado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se pudo eliminar el platillo");
        }
    }
    @Transactional(readOnly = true)
    public Response<Platillo> getOne(Long id) {
        if (this.repository.findByIdPlatillo(id) != null) {
            Platillo platillo = this.repository.findByIdPlatillo(id);
            if (platillo != null) {
                return new Response<>(platillo, false, 200, "Correcto");
            } else {
                return new Response<>(null, true, 400, "No se encontro el platillo");
            }
        } else {
            return new Response<>(null, true, 400, "No se encontro el platillo");
        }
    }



}

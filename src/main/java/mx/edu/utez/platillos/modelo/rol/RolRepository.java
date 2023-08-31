package mx.edu.utez.platillos.modelo.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository

public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);


}

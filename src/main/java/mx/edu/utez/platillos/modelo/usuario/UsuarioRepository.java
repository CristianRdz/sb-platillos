package mx.edu.utez.platillos.modelo.usuario;

import mx.edu.utez.platillos.modelo.rol.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);
    List<Usuario> findByEstatus(Boolean estatus);

    @Query(value = "SELECT * FROM usuarios WHERE id_usuario = :id", nativeQuery = true)
    Usuario findByIdUsuario(@Param("id") Long id);

    @Query(value = "SELECT * FROM usuarios WHERE fk_rol = :idRol and estatus = :estatus", nativeQuery = true)
    List<Usuario> findByRolAndEstatus(@Param("idRol") Long idRol, @Param("estatus") Boolean estatus);



}

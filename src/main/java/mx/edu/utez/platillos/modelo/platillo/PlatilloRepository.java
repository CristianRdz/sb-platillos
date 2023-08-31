package mx.edu.utez.platillos.modelo.platillo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
    @Query(value = "SELECT * FROM platillos WHERE estatus=:estatus and id_platillo = :id", nativeQuery = true)
    Platillo findByIdAndEstatus(Boolean estatus, Long id);

    @Query(value = "SELECT * FROM platillos WHERE id_platillo = :id", nativeQuery = true)
    Platillo findByIdPlatillo(Long id);


    Platillo findByNombre(String nombre);

    @Query(value = "SELECT * FROM platillos WHERE fk_categoria = :id", nativeQuery = true)
    List<Platillo> findByCategoria(Long id);
    List<Platillo> findByEstatus (Boolean estatus);
    List<Platillo> findByNombreContaining(String nombre);
    List<Platillo> findByEstatusAndNombreContaining(Boolean estatus, String nombre);
}

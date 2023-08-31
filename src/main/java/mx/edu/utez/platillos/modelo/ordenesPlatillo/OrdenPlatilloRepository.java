package mx.edu.utez.platillos.modelo.ordenesPlatillo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenPlatilloRepository extends JpaRepository<OrdenPlatillo, Long> {
    @Query(value = "SELECT * FROM ordenes_platillos WHERE id_elemento = :id", nativeQuery = true)
    OrdenPlatillo findByIdOrdenPlatillo(long id);

    @Query(value = "SELECT * FROM ordenes_platillos", nativeQuery = true)
    List<OrdenPlatillo> getAll();

    @Query(value = "SELECT * FROM ordenes_platillos WHERE  fk_orden = :id", nativeQuery = true)
    List<OrdenPlatillo> findByIdOrden(long id);
    @Query(value = "SELECT * FROM ordenes_platillos WHERE  fk_platillo = :id", nativeQuery = true)
    List<OrdenPlatillo> findByPlatillo(long id);
    @Query(value = "SELECT * FROM ordenes_platillos WHERE  fk_tamanio = :id", nativeQuery = true)
    List<OrdenPlatillo> findByTamanio(long id);


}

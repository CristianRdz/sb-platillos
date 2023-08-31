package mx.edu.utez.platillos.modelo.orden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

        @Query(value = "SELECT * FROM ordenes WHERE id_orden = :id", nativeQuery = true)
        Orden findByIdOrden(@Param("id") Long id);

        @Query(value = "SELECT * FROM ordenes WHERE id_orden = :id AND estatus = :estatus", nativeQuery = true)
        Orden findByIdOrdenAndEstatus(@Param("id") Long id, @Param("estatus") Boolean estatus);

        @Query(value = "SELECT * FROM ordenes WHERE fk_usuario = :id", nativeQuery = true)
        List<Orden> findByIdUsuario(@Param("id") Long id);

        @Query(value = "SELECT * FROM ordenes WHERE fk_usuario = :id AND estatus = 1", nativeQuery = true)
        Orden findByIdUsuarioAndEstatus(@Param("id") Long id);

        @Query(value = "SELECT SUM(platillos.precio) AS suma_precios\n" +
                "FROM ordenes\n" +
                "INNER JOIN ordenes_platillos\n" +
                "ON ordenes_platillos.fk_orden = ordenes.id_orden\n" +
                "INNER JOIN platillos\n" +
                "ON ordenes_platillos.fk_platillo = platillos.id_platillo\n" +
                "WHERE ordenes.fecha_creacion AND ordenes.estatus = 0", nativeQuery = true)
        String gananciasTotales();
        // lo mismo pero en rango de fechas
        @Query(value = "SELECT SUM(platillos.precio) AS suma_precios\n" +
                "FROM ordenes\n" +
                "INNER JOIN ordenes_platillos\n" +
                "ON ordenes_platillos.fk_orden = ordenes.id_orden\n" +
                "INNER JOIN platillos\n" +
                "ON ordenes_platillos.fk_platillo = platillos.id_platillo\n" +
                "WHERE ordenes.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND ordenes.estatus = 0", nativeQuery = true)
        // 2021-05-01 00:00:00 2021-05-31 23:59:59 solo se tomara la primera fecha y la otra parte del timestamp se agregara en el frontend
        String gananciasTotalesByFecha(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
        List<Orden> findByEstatus(Boolean estatus);

}

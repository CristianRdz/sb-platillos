package mx.edu.utez.platillos.modelo.tamanio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanioRepository extends JpaRepository<Tamanio, Long> {
    Tamanio findByNombre(String nombre);
}

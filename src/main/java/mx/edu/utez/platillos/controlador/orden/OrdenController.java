package mx.edu.utez.platillos.controlador.orden;

import mx.edu.utez.platillos.controlador.orden.dtos.OrdenDto;
import mx.edu.utez.platillos.modelo.orden.Orden;
import mx.edu.utez.platillos.servicios.orden.OrdenService;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
@CrossOrigin(origins = {"*"})
public class OrdenController {
    @Autowired
    private OrdenService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Orden>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Orden>> getOne(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<Response<List<Orden>>> getAllByEstatus(
            @PathVariable("estatus") Boolean estatus
    ) {
        return new ResponseEntity<>(this.service.getAllByEstatus(estatus), HttpStatus.OK);
    }
    @GetMapping("/ganancias")
    public ResponseEntity<Response<String>> getGananciasTotales() {
        return new ResponseEntity<>(this.service.getGananciasTotales(), HttpStatus.OK);
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Response<List<Orden>>> getAllByUsuario(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getAllByUsuario(id), HttpStatus.OK);
    }
    @GetMapping("/usuario/actual/{id}")
    public ResponseEntity<Response<Orden>> getOneByUsuario(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getOneByUsuario(id), HttpStatus.OK);
    }
    @GetMapping("/ganancias/{fechaInicio}/{fechaFin}")
    public ResponseEntity<Response<String>> getGananciasRango(
            @PathVariable("fechaInicio") String fechaInicio,
            @PathVariable("fechaFin") String fechaFin
    ) {
        return new ResponseEntity<>(this.service.getGananciasRango(fechaInicio,fechaFin), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Response<Orden>> insert(@RequestBody OrdenDto Orden) {
        return new ResponseEntity<>(
                this.service.insert(Orden.getOrden()), HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Response<Orden>> update(@RequestBody OrdenDto Orden) {
        return new ResponseEntity<>(
                this.service.update(Orden.getOrden()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Orden>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id), HttpStatus.OK
        );
    }
}

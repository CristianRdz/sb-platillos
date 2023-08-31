package mx.edu.utez.platillos.controlador.ordenesPlatillo;

import mx.edu.utez.platillos.controlador.ordenesPlatillo.dtos.OrdenPlatilloDto;
import mx.edu.utez.platillos.modelo.ordenesPlatillo.OrdenPlatillo;
import mx.edu.utez.platillos.servicios.ordenesPlatillo.OrdenPlatilloService;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenplatillo")
@CrossOrigin(origins = {"*"})
public class OrdenPlatilloController {
    @Autowired
    private OrdenPlatilloService service;


    @GetMapping("/")
    public ResponseEntity<Response<List<OrdenPlatillo>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<OrdenPlatillo>> getOne(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/orden/{id}")
    public ResponseEntity<Response<List<OrdenPlatillo>>> getOneOrden(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getAllByOrden(id), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Response<OrdenPlatillo>> insert(@RequestBody OrdenPlatilloDto OrdenPlatillo) {
        return new ResponseEntity<>(
                this.service.insert(OrdenPlatillo.getOrdenPlatillo()), HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Response<OrdenPlatillo>> update(@RequestBody OrdenPlatilloDto OrdenPlatillo) {
        return new ResponseEntity<>(
                this.service.update(OrdenPlatillo.getOrdenPlatillo()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<OrdenPlatillo>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id), HttpStatus.OK
        );
    }
}

package mx.edu.utez.platillos.controlador.platillo;
import mx.edu.utez.platillos.controlador.platillo.dtos.PlatilloDto;
import mx.edu.utez.platillos.modelo.platillo.Platillo;
import mx.edu.utez.platillos.servicios.platillo.PlatilloService;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platillo")
@CrossOrigin(origins = {"*"})
public class PlatilloController {
    @Autowired
    private PlatilloService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Platillo>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Platillo>> getOne(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<Response<List<Platillo>>> getAllByEstatus(
            @PathVariable("estatus") Boolean estatus
    ) {
        return new ResponseEntity<>(this.service.getAllByEstatus(estatus), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Response<Platillo>> insert(@RequestBody PlatilloDto Platillo) {
        return new ResponseEntity<>(
                this.service.insert(Platillo.getPlatillo()), HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Response<Platillo>> update(@RequestBody PlatilloDto Platillo) {
        return new ResponseEntity<>(
                this.service.update(Platillo.getPlatillo()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Platillo>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id), HttpStatus.OK
        );
    }
}

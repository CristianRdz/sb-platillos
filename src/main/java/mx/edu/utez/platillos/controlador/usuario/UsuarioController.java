package mx.edu.utez.platillos.controlador.usuario;
import mx.edu.utez.platillos.controlador.usuario.dtos.UsuarioDto;
import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.servicios.usuario.UsuarioService;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"*"})
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Usuario>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Usuario>> getOne(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<Response<List<Usuario>>> getAllByEstatus(
            @PathVariable("estatus") Boolean estatus
    ) {
        return new ResponseEntity<>(this.service.getAllByEstatus(estatus), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Response<Usuario>> insert(@RequestBody UsuarioDto usuario) {
        return new ResponseEntity<>(
                this.service.insert(usuario.getUsuario()), HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Response<Usuario>> update(@RequestBody UsuarioDto usuario) {
        return new ResponseEntity<>(
                this.service.update(usuario.getUsuario()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id), HttpStatus.OK
        );
    }

    @PutMapping("/pass")
    public ResponseEntity<Response<Boolean>> updatePass(@RequestBody UsuarioDto usuario) {
        return new ResponseEntity<>(
                this.service.updatePassword(usuario.getUsuario()), HttpStatus.OK
        );
    }

}

package mx.edu.utez.platillos.servicios.usuario;

import mx.edu.utez.platillos.modelo.rol.RolRepository;
import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.modelo.usuario.UsuarioRepository;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;


    @Transactional(readOnly = true)
    public Response<List<Usuario>> getAll() {
        return new Response<>(this.repository.findAll(), false, 200, "Correcto");
    }

    @Transactional(readOnly = true)
    public Response<List<Usuario>> getAllByEstatus(Boolean estatus) {
        return new Response<>(this.repository.findByEstatus(estatus), false, 200, "Correcto");
    }




    @Transactional(readOnly = true)
    public Response<Usuario> getOne(Long id) {
        if (this.repository.findByIdUsuario(id) != null) {
            Usuario usuario = this.repository.findByIdUsuario(id);
            if (usuario != null) {
                return new Response<>(usuario, false, 200, "Correcto");
            } else {
                return new Response<>(null, true, 400, "No se encontro el usuario");
            }
        } else {
            return new Response<>(null, true, 400, "No se encontro el usuario");
        }

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Usuario> insert(Usuario usuario) {
        if (this.repository.findByCorreo(usuario.getCorreo()) == null) {
            if (usuario.getRol() != null) {
                usuario.setContrasena(encoder.encode(usuario.getContrasena()));
                return new Response<>(this.repository.save(usuario), false, 200, "Usuario registrado correctamente");
            } else {
                return new Response<>(null, true, 400, "No se encontro el rol");
            }

        } else {
            return new Response<>(null, true, 400, "El correo ya esta registrado");
        }

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Usuario> update(Usuario usuario) {
        if (this.repository.existsById(usuario.getId_usuario())) {
            this.repository.saveAndFlush(usuario);
            return new Response<>(usuario, false, 200, "Usuario actualizado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se encontro el usuario");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(Long id) {
        if (this.repository.existsById(id)) {
            Usuario usuario = this.repository.getOne(id);
            usuario.setEstatus(!usuario.getEstatus());
            this.repository.saveAndFlush(usuario);
            return new Response<>(true, false, 200, "Usuario eliminado correctamente");
        } else {
            return new Response<>(null, true, 400, "No se encontro el usuario");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> updatePassword(Usuario usuario) {
        if (this.repository.existsById(usuario.getId_usuario()) && usuario.getEstatus()) {
            usuario.setContrasena(encoder.encode(usuario.getContrasena()));
            this.repository.saveAndFlush(usuario);
            return new Response<>(true, false, 200, "Contraseña actualizada correctamente");
        } else {
            return new Response<>(null, true, 400, "No se encontro el usuario");
        }
    }

    @Transactional(readOnly = true)
    public Usuario findByCorreo(String correo) {
        return this.repository.findByCorreo(correo);
    }
}

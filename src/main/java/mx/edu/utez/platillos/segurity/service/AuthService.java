package mx.edu.utez.platillos.segurity.service;

import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.segurity.model.AuthUser;
import mx.edu.utez.platillos.servicios.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    UsuarioService service;
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = this.service.findByCorreo(correo);
        return AuthUser.build(usuario);
    }
}

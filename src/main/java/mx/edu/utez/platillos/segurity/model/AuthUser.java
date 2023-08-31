package mx.edu.utez.platillos.segurity.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.edu.utez.platillos.modelo.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUser implements UserDetails{

    private String correo;
    @JsonIgnore
    private String contrasena;

    private Usuario usuario;
    public Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String correo, String contrasena, Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.usuario = usuario;
        this.authorities = authorities;
    }

    public static AuthUser build(Usuario usuario){
        GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        return new AuthUser(usuario.getCorreo(), usuario.getContrasena(), usuario, List.of(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}

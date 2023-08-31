package mx.edu.utez.platillos.segurity.jwt;
import mx.edu.utez.platillos.segurity.model.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;
@Component
public class JwtProvider {
    private  final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        AuthUser user = (AuthUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                new Date().getTime() + expiration * 1000L
                        )
                )
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("Token no soportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("Token caducado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token no provisto");
        }catch (SignatureException e){
            LOGGER.error("Error en la firma del token");
        }
        return false;
    }
    public String generatePasswordResetToken(String email) {
        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                new Date().getTime() + expiration * 1000L
                        )
                )
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validatePasswordResetToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            LOGGER.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token caducado");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token no provisto");
        } catch (SignatureException e) {
            LOGGER.error("Error en la firma del token");
        }
        return false;
    }

    public String getEmailFromPasswordResetToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }


}

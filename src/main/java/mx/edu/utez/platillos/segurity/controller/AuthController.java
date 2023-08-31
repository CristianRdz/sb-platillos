package mx.edu.utez.platillos.segurity.controller;

import mx.edu.utez.platillos.modelo.usuario.Usuario;
import mx.edu.utez.platillos.segurity.controller.dto.LoginDto;
import mx.edu.utez.platillos.segurity.controller.dto.CambioRequestDto;
import mx.edu.utez.platillos.segurity.controller.dto.CambioResponseDTO;
import mx.edu.utez.platillos.segurity.controller.dto.ResetPasswordDTO;
import mx.edu.utez.platillos.segurity.jwt.JwtProvider;
import mx.edu.utez.platillos.servicios.usuario.UsuarioService;
import mx.edu.utez.platillos.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtProvider provider;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${spring.mail.username}")
    private String mailFrom;
    @Autowired
    UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<Response<Object>> login(
            @Valid @RequestBody LoginDto login
    ){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getCorreo(), login.getContrasena()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", userDetails);
        return new ResponseEntity<>(
                new Response<>(data,false,200,"OK"),
                HttpStatus.OK
        );
    }
    @PostMapping("/reset-password")
    public CambioResponseDTO requestPasswordReset(@Valid @RequestBody CambioRequestDto datosRequest) throws MessagingException {
        String host = "http://localhost:3000";
        // Obtener el usuario a partir del correo electrónico
        Usuario user = service.findByCorreo(datosRequest.getCorreo());

        // Verificar que el usuario existe
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo electrónico " + datosRequest.getCorreo());
        }

        // Generar un token JWT para restablecer la contraseña
        String token = provider.generatePasswordResetToken(user.getCorreo());

        // Crear el correo electrónico
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getCorreo());
        helper.setFrom(mailFrom);
        helper.setSubject("Solicitud de restablecimiento de contraseña");
        helper.setText("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Recuperacion de contraseña</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
                "            font-size: 14px;\n" +
                "            line-height: 1.6;\n" +
                "            color: #555;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            border: 1px solid #ddd;\n" +
                "            border-radius: 20px;\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "        .logo {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .logo img {\n" +
                "            max-width: 15%;\n" +
                "            height: auto;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            margin-top: 20px;\n" +
                "            text-align: center;\n" +
                "            color: #888;\n" +
                "        }\n" +
                "        .boton{\n" +
                "            background-color: #179275;\n" +
                "            border: none;\n" +
                "            padding: 15px 32px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 16px;\n" +
                "            margin: 4px 2px;\n" +
                "            cursor: pointer;\n" +
                "            border-radius: 20px;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <p>¡Hola! " + user.getNombre() +"</p>\n" +
                "    <p>Por favor, haz click en el siguiente enlace para cambiar tu contraseña:</p>\n" +
                "\n" +
                "    <a class=\"boton\" href=\"" + host + "/confirmar?token=" + token + "\" style='color:white;'>Cambiar contraseña</a>\n" +
                "    <div class=\"footer\">\n" +
                "        Este correo electrónico fue enviado automáticamente. Por favor, no\n" +
                "        respondas a este correo electrónico.\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>", true);

        // Enviar el correo electrónico
        javaMailSender.send(message);

        // Devolver una respuesta indicando que se ha enviado el correo electrónico
        CambioResponseDTO response = new CambioResponseDTO();
        response.setMensaje("Se ha enviado un correo electrónico a " +

                user.getCorreo() + " con las instrucciones para restablecer tu contraseña.");
        return response;
    }
    @PostMapping("/reset-password/confirm")
    public CambioResponseDTO resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordData) {
        // Obtener los datos del usuario a partir del token
        String user = provider.getEmailFromPasswordResetToken(resetPasswordData.getToken());
        // Verificar que el token sea válido y no haya expirado
        if (user == null) {
            throw new RuntimeException("Token inválido o ha expirado");
        }
        // Actualizar la contraseña del usuario
        Usuario usuario = service.findByCorreo(user);
        usuario.setContrasena(resetPasswordData.getPassword());
        service.updatePassword(usuario);
        // Devolver una respuesta indicando que la contraseña se ha restablecido
        CambioResponseDTO response = new CambioResponseDTO();
        response.setMensaje("Tu contraseña se ha restablecido correctamente.");
        return response;
    }

}

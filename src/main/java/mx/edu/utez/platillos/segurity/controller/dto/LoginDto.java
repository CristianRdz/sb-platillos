package mx.edu.utez.platillos.segurity.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
    @NotEmpty
    @NotBlank
    private String correo;
    @NotEmpty
    @NotBlank
    private String contrasena;
}

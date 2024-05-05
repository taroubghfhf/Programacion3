package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;
import lombok.Setter;

import static co.edu.uniquindio.noticias.utils.validaciones.Validador.validadorNoNull;
import static co.edu.uniquindio.noticias.utils.validaciones.Validador.validadorCorreo;

@Getter
@Setter
public class Usuario {
    private static final String CAMPO_CORREO_OBLIGATORIO= "Debe ingresar un correo";
    private static final String CAMPO_PASSWORD_OBLIGATORIO= "Debe ingresar una contraseña";
    private static final String CORREO_INVALIDO= "Debe ingresar correo valido";

    private String correo;
    private String password;
    private TipoUsuario tipoUsuario;

    public Usuario(String correo, String password, TipoUsuario tipoUsuario) {
        validadorNoNull(correo,CAMPO_CORREO_OBLIGATORIO);
        validadorNoNull(password,CAMPO_PASSWORD_OBLIGATORIO);
        validadorCorreo(correo,CORREO_INVALIDO);
        this.correo = correo;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }
}

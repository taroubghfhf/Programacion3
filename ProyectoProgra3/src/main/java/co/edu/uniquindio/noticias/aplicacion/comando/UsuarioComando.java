package co.edu.uniquindio.noticias.aplicacion.comando;

import co.edu.uniquindio.noticias.dominio.model.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import static co.edu.uniquindio.noticias.utils.validaciones.Validador.validadorCorreo;
import static co.edu.uniquindio.noticias.utils.validaciones.Validador.validadorNoNull;

@Getter
@Setter
public class UsuarioComando {

    private String correo;
    private String password;
    private TipoUsuario tipoUsuario;

    public UsuarioComando(String correo, String password, TipoUsuario tipoUsuario) {
        this.correo = correo;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }
}

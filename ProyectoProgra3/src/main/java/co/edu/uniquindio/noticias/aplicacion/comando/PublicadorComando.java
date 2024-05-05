package co.edu.uniquindio.noticias.aplicacion.comando;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicadorComando extends PersonaComando {
    public PublicadorComando(String nombre, String apellido, String identificacion, String telefono, UsuarioComando usuarioComando) {
        super(nombre, apellido, identificacion, telefono, usuarioComando);
    }
}

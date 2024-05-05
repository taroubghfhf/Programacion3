package co.edu.uniquindio.noticias.aplicacion.comando;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorComando extends PersonaComando {

    public AdministradorComando(String nombre, String apellido, String identificacion, String telefono, UsuarioComando usuarioComando) {
        super(nombre, apellido, identificacion, telefono, usuarioComando);
    }
}



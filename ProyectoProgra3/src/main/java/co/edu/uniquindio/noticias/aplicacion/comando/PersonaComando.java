package co.edu.uniquindio.noticias.aplicacion.comando;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonaComando {

    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private UsuarioComando usuarioComando;

    public PersonaComando(String nombre, String apellido, String identificacion, String telefono, UsuarioComando usuarioComando) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.usuarioComando = usuarioComando;
    }
}
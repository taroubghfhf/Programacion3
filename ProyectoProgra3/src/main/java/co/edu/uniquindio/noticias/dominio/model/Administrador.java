package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrador extends Persona {

    public Administrador(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        super(nombre, apellido, identificacion, telefono, usuario);
    }
}



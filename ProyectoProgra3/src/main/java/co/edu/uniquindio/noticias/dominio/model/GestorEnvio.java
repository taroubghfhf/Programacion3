package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorEnvio extends Persona{


    public GestorEnvio(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        super(nombre, apellido, identificacion, telefono, usuario);
    }
}
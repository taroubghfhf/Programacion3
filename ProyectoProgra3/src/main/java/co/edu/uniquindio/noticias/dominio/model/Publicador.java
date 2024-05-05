package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Publicador extends Persona {
    private String ruta;
    private Contenido contenido;

    public Publicador(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        super(nombre, apellido, identificacion, telefono, usuario);
    }
}
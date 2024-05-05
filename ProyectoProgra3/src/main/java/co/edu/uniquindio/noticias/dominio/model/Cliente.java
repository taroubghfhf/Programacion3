package co.edu.uniquindio.noticias.dominio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {
    private String ruta;

    public Cliente(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        super(nombre, apellido, identificacion, telefono, usuario);
    }
}
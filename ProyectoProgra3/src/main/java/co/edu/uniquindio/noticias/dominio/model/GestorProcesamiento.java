package co.edu.uniquindio.noticias.dominio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GestorProcesamiento extends Persona{
    private List<String> archivosPendientes;
    private List<String> archivosProcesados;
    private String estadoProcesamiento;

    public GestorProcesamiento(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        super(nombre, apellido, identificacion, telefono, usuario);
    }
}

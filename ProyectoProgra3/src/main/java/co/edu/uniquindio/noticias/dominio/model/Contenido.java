package co.edu.uniquindio.noticias.dominio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenido  {
    private List<String> archivosProcesados;
    private  List<String> archivosEnviado;

}

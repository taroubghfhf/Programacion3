package co.edu.uniquindio.noticias.dominio.servicios.publicador;

import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorEnviar;

public class SubirArchivoServicio {
    private static String NOMBRE_CARPETA = "publicadores";
    private static PublicadorEnviar publicadorEnviar;
    public SubirArchivoServicio(PublicadorEnviar publicadorEnviar) {
        this.publicadorEnviar = publicadorEnviar;
    }

    public void ejecutar(String rutaArchivo, Publicador publicador) {
        this.publicadorEnviar.enviar(rutaArchivo, publicador.getIdentificacion());
    }
}

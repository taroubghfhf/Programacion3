package co.edu.uniquindio.noticias.aplicacion.manejador.publicador;

import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.servicios.publicador.SubirArchivoServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.publicador.PublicadorImplementacionEnviar;

public class ManejadorSubirArchivo {

    private PublicadorImplementacionEnviar publicadorImplementacionEnviar;

    public ManejadorSubirArchivo() {
        this.publicadorImplementacionEnviar = new PublicadorImplementacionEnviar();
    }

    public void ejecutar(String ruta,Publicador publicador){
        SubirArchivoServicio servicio = new SubirArchivoServicio(publicadorImplementacionEnviar);
        servicio.ejecutar(ruta, publicador);
    }
}

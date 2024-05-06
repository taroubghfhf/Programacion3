package co.edu.uniquindio.noticias.aplicacion.manejador.gestorprocesamiento;

import co.edu.uniquindio.noticias.dominio.servicios.gestorprocesamiento.ProcesarInformacionServicio;

public class ManejadorProcesarInformacion {

    public void ejecutar(){
        ProcesarInformacionServicio procesarInformacionServicio = new ProcesarInformacionServicio();
        procesarInformacionServicio.ejecutar();
    }
}

package co.edu.uniquindio.noticias.aplicacion.manejador.gestorenvio;

import co.edu.uniquindio.noticias.dominio.servicios.gestorenvio.ProcesarEnvioServicio;

public class ManejadorProcesarEnvio {

    public void ejecutar(){
        ProcesarEnvioServicio servicio = new ProcesarEnvioServicio();
        servicio.ejecutar();
    }
}

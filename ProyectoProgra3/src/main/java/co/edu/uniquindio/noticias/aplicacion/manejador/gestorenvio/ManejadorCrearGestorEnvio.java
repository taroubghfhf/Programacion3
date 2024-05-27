package co.edu.uniquindio.noticias.aplicacion.manejador.gestorenvio;

import co.edu.uniquindio.noticias.aplicacion.comando.GestorEnvioComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.gestorenvio.FabricaGestorEnvio;
import co.edu.uniquindio.noticias.dominio.model.GestorEnvio;
import co.edu.uniquindio.noticias.dominio.servicios.gestorenvio.CrearGestorEnvioServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.gestorenvio.GestorEnvioImplementacionDAO;
import co.edu.uniquindio.noticias.infaestructura.adaptador.gestorenvio.GestorEnvioImplementacionRepositorio;

public class ManejadorCrearGestorEnvio {

    private GestorEnvioImplementacionDAO gestorEnvioImplementacionDAO;
    private GestorEnvioImplementacionRepositorio gestorEnvioImplementacionRepositorio;

    public ManejadorCrearGestorEnvio() {
        this.gestorEnvioImplementacionDAO = new GestorEnvioImplementacionDAO();
        this.gestorEnvioImplementacionRepositorio = new GestorEnvioImplementacionRepositorio();
    }

    public void ejecutar(GestorEnvioComando gestorEnvioComando){
        GestorEnvio gestorEnvio = FabricaGestorEnvio.crear(gestorEnvioComando);
        CrearGestorEnvioServicio servicio = new CrearGestorEnvioServicio(gestorEnvioImplementacionDAO,
                gestorEnvioImplementacionRepositorio);
        servicio.crear(gestorEnvio);
    }
}

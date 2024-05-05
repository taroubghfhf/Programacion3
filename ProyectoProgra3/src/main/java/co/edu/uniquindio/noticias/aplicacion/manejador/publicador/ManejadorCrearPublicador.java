package co.edu.uniquindio.noticias.aplicacion.manejador.publicador;

import co.edu.uniquindio.noticias.aplicacion.comando.PublicadorComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.publicador.FabricaPublicador;
import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.servicios.publicador.CrearPublicadorServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.publicador.PublicadorImplementacionDAO;
import co.edu.uniquindio.noticias.infaestructura.adaptador.publicador.PublicadorImplementacionRepositorio;

public class ManejadorCrearPublicador {

    private PublicadorImplementacionDAO publicadorImplementacionDAO;
    private PublicadorImplementacionRepositorio publicadorImplementacionRepositorio;

    public ManejadorCrearPublicador() {
        this.publicadorImplementacionDAO = new PublicadorImplementacionDAO();
        this.publicadorImplementacionRepositorio = new PublicadorImplementacionRepositorio();
    }

    public void ejecutar(PublicadorComando publicadorComando){
        Publicador publicador = FabricaPublicador.crear(publicadorComando);
        CrearPublicadorServicio servicio = new CrearPublicadorServicio(publicadorImplementacionDAO,
                publicadorImplementacionRepositorio);
        servicio.crear(publicador);
    }
}

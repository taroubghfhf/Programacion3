package co.edu.uniquindio.noticias.aplicacion.manejador.administrador;

import co.edu.uniquindio.noticias.dominio.model.Administrador;
import co.edu.uniquindio.noticias.dominio.servicios.administrador.CrearAdministradorServicio;
import co.edu.uniquindio.noticias.aplicacion.comando.AdministradorComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.administrador.FabricaAdministrador;
import co.edu.uniquindio.noticias.infaestructura.adaptador.administrador.AdministradorImplementacionDAO;
import co.edu.uniquindio.noticias.infaestructura.adaptador.administrador.AdministradorImplementacionRepositorio;

public class ManejadorCrearAdministrador {

    private AdministradorImplementacionDAO  administradorImplementacionDAO;
    private AdministradorImplementacionRepositorio administradorImplementacionRepositorio;

    public ManejadorCrearAdministrador() {
        this.administradorImplementacionDAO = new AdministradorImplementacionDAO();
        this.administradorImplementacionRepositorio = new AdministradorImplementacionRepositorio();
    }

    public void ejecutar(AdministradorComando administradorComando){
        Administrador administrador = FabricaAdministrador.crear(administradorComando);
        CrearAdministradorServicio servicio = new CrearAdministradorServicio(administradorImplementacionDAO,
                administradorImplementacionRepositorio);
        servicio.crear(administrador);
    }

}

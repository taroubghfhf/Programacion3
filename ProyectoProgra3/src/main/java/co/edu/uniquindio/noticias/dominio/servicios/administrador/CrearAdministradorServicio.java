package co.edu.uniquindio.noticias.dominio.servicios.administrador;

import co.edu.uniquindio.noticias.dominio.exception.YaExisteUsuario;
import co.edu.uniquindio.noticias.dominio.model.Administrador;
import co.edu.uniquindio.noticias.dominio.puerto.administrador.AdministradorDAO;
import co.edu.uniquindio.noticias.dominio.puerto.administrador.AdministradorRepositorio;
import co.edu.uniquindio.noticias.infaestructura.vista.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrearAdministradorServicio {
    private static final Logger logger = LoggerFactory.getLogger(CrearAdministradorServicio.class);

    private static final String YA_EXISTE_USUARIO="el usuario ya existe";
    private static AdministradorDAO administradorDAO;
    private static AdministradorRepositorio administradorRepositorio;

    public CrearAdministradorServicio(AdministradorDAO administradorDAO,AdministradorRepositorio administradorRepositorio) {
        this.administradorDAO=administradorDAO;
        this.administradorRepositorio=administradorRepositorio;
    }

    public void crear(Administrador administrador){
        logger.info("Flujo crear administrador");
        if(administradorDAO.validarExistencia(administrador.getUsuario().getCorreo())){
            throw new YaExisteUsuario(YA_EXISTE_USUARIO);
        }
        this.administradorRepositorio.crear(administrador);
    }
}

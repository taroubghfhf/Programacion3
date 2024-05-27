package co.edu.uniquindio.noticias.dominio.servicios.gestorenvio;

import co.edu.uniquindio.noticias.dominio.exception.YaExisteUsuario;
import co.edu.uniquindio.noticias.dominio.model.GestorEnvio;
import co.edu.uniquindio.noticias.dominio.puerto.gestorenvio.GestorEnvioDAO;
import co.edu.uniquindio.noticias.dominio.puerto.gestorenvio.GestorEnvioRepositorio;

public class CrearGestorEnvioServicio {

    private static final String YA_EXISTE_USUARIO = "El Gestor envio ya existe";
    private static GestorEnvioDAO gestorEnvioDAO;
    private static GestorEnvioRepositorio gestorEnvioRepositorio;

    public CrearGestorEnvioServicio(GestorEnvioDAO gestorEnvioDAO,
                                    GestorEnvioRepositorio gestorEnvioRepositorio ){
        this.gestorEnvioDAO = gestorEnvioDAO;
        this.gestorEnvioRepositorio = gestorEnvioRepositorio;
    }

    public void crear(GestorEnvio gestorEnvio) {
        if (gestorEnvioDAO.validarExistencia(gestorEnvio.getUsuario().getCorreo())) {
            throw new YaExisteUsuario(YA_EXISTE_USUARIO);
        }
        this.gestorEnvioRepositorio.crear(gestorEnvio);
    }
}

package co.edu.uniquindio.noticias.dominio.servicios.publicador;

import co.edu.uniquindio.noticias.dominio.exception.YaExisteUsuario;
import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorDAO;
import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorRepositorio;
import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;

import java.io.File;

public class CrearPublicadorServicio {

    public static final String RUTA_BASE = ArchivoConfig.getProperty("ruta.base");
    public static final String PUBLICADORES = "publicadores";
    private static final String YA_EXISTE_PUBLICADOR = "El publicador ya existe";
    private static final String YA_EXISTE_CARPETA = "El publicador ya tienen una carpeta creada";
    private static final String CREAR_CARPETA_ERROR = "Error creando la carpeta del publicador";
    private static PublicadorDAO publicadorDAO;
    private static PublicadorRepositorio publicadorRepositorio;

    public CrearPublicadorServicio(PublicadorDAO publicadorDAO, PublicadorRepositorio publicadorRepositorio) {
        this.publicadorDAO = publicadorDAO;
        this.publicadorRepositorio = publicadorRepositorio;
    }

    public void crear(Publicador publicador) {
        if (publicadorDAO.validarExistencia(publicador.getUsuario().getCorreo())) {
            throw new YaExisteUsuario(YA_EXISTE_PUBLICADOR);
        }
        this.publicadorRepositorio.crear(publicador);
        crearArchivo(publicador);
    }

    private void crearArchivo(Publicador publicador) {
        publicador.setRuta(RUTA_BASE + PUBLICADORES + "\\" + publicador.getIdentificacion());
        File carpeta = new File(publicador.getRuta());

        if (carpeta.exists()) {
            throw new RuntimeException(YA_EXISTE_CARPETA);
        }

        if (!carpeta.mkdirs()) {
            throw new RuntimeException(CREAR_CARPETA_ERROR);
        }
    }
}

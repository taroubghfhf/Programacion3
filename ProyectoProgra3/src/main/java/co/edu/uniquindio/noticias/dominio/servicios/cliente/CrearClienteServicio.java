package co.edu.uniquindio.noticias.dominio.servicios.cliente;

import co.edu.uniquindio.noticias.dominio.exception.YaExisteUsuario;
import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.puerto.cliente.ClienteDAO;
import co.edu.uniquindio.noticias.dominio.puerto.cliente.ClienteRepositorio;
import co.edu.uniquindio.noticias.dominio.servicios.administrador.CrearAdministradorServicio;
import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CrearClienteServicio {

    private static final Logger logger = LoggerFactory.getLogger(CrearClienteServicio.class);


    public static final String RUTA_BASE = ArchivoConfig.getProperty("ruta.base");
    public static final String CLIENTES = "clientes";
    private static final String YA_EXISTE_USUARIO = "El cliente ya existe";
    private static final String YA_EXISTE_CARPETA = "El cliente ya tienen una carpeta creada";
    private static final String CREAR_CARPETA_ERROR = "Error creando la carpeta del cliente";
    private static ClienteDAO clienteDAO;
    private static ClienteRepositorio clienteRepositorio;

    public CrearClienteServicio(ClienteDAO clienteDAO, ClienteRepositorio clienteRepositorio) {
        this.clienteDAO = clienteDAO;
        this.clienteRepositorio = clienteRepositorio;
    }

    public void crear(Cliente cliente) {
        logger.info("Creando cliente");
        if (clienteDAO.validarExistencia(cliente.getUsuario().getCorreo())) {
            throw new YaExisteUsuario(YA_EXISTE_USUARIO);
        }
        this.clienteRepositorio.crear(cliente);
        crearArchivo(cliente);
    }

    private void crearArchivo(Cliente cliente) {
        cliente.setRuta(RUTA_BASE + CLIENTES + "\\" + cliente.getIdentificacion());
        File carpeta = new File(cliente.getRuta());

        if (carpeta.exists()) {
            logger.warn("ya existe la carpeta cliente");
            throw new RuntimeException(YA_EXISTE_CARPETA);
        }

        if (!carpeta.mkdirs()) {
            logger.warn("No se puede crear carpeta cliente");
            throw new RuntimeException(CREAR_CARPETA_ERROR);
        }
    }
}

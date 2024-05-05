// Paquete que contiene los servicios relacionados con los usuarios
// package co.edu.uniquindio.noticiasUQ.servicios.usuario;

// Importaciones de clases y excepciones necesarias
// import co.edu.uniquindio.noticiasUQ.model.Cliente;
// import dominio.exepcion.ErrorCreandoDirectorioException;
// import dominio.exepcion.YaExisteUsuarioExcepcion;
// import co.edu.uniquindio.Almacenamiento;

// Importación de la clase File para manejo de archivos y directorios
// import java.io.File;

// Clase que define el servicio relacionado con los clientes
// public class Cliente_Servicio {
// Constantes para manejo de mensajes de error
// private static final String ERROR_CREANDO_DIRECTORIO = "No se pudo crear carpeta ";
// private static final String YA_EXISTE_CLIENTE = "El cliente ya existe ";

// Atributo para almacenamiento de clientes
// private Almacenamiento almacenamiento;

// Constructor que recibe el almacenamiento como parámetro
// public Cliente_Servicio(Almacenamiento almacenamiento) {
// this.almacenamiento = almacenamiento;
// }

// Método para crear un cliente
// public void crearCliente(Cliente cliente) {
// Verifica si el cliente ya existe
// if (almacenamiento.validarExistenciaPreviaPublicada(cliente) == null) {
// Crea la carpeta del cliente y guarda el cliente
// crearCarpeta(cliente.getRuta());
// almacenamiento.guardarCliente(cliente);
// } else {
// Lanza una excepción si el cliente ya existe
// throw new YaExisteUsuarioExcepcion(YA_EXISTE_CLIENTE);
// }
// }

// Método privado para crear una carpeta
// private void crearCarpeta(String ruta) {
// Crea un objeto File con la ruta especificada
// File directorio = new File(ruta);
// Verifica si el directorio no existe
// if (!directorio.exists()) {
// Intenta crear el directorio y lanza una excepción si falla
// if (!directorio.mkdirs()) {
// throw new ErrorCreandoDirectorioException(ERROR_CREANDO_DIRECTORIO);
// }
// }
// }
// }

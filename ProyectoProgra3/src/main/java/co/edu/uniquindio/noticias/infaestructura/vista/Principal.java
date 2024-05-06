package co.edu.uniquindio.noticias.infaestructura.vista;

import co.edu.uniquindio.noticias.aplicacion.comando.*;
import co.edu.uniquindio.noticias.aplicacion.manejador.cliente.ManejadorCrearCliente;
import co.edu.uniquindio.noticias.aplicacion.manejador.gestorprocesamiento.ManejadorCrearGestorProcesamiento;
import co.edu.uniquindio.noticias.aplicacion.manejador.gestorprocesamiento.ManejadorProcesarInformacion;
import co.edu.uniquindio.noticias.aplicacion.manejador.iniciarsesion.ManejadorIniciarSesion;
import co.edu.uniquindio.noticias.aplicacion.manejador.publicador.ManejadorCrearPublicador;
import co.edu.uniquindio.noticias.aplicacion.manejador.publicador.ManejadorSubirArchivo;
import co.edu.uniquindio.noticias.dominio.model.*;
import co.edu.uniquindio.noticias.aplicacion.manejador.administrador.ManejadorCrearAdministrador;
import co.edu.uniquindio.noticias.infaestructura.socket.ServidorSocket;
import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class Principal {

    public static final String RUTA_CSV = ArchivoConfig.getProperty("ruta.base");

    public static final String DATOS = "datos.csv";
    private static ServidorSocket servidorSocket;
    private static ManejadorCrearAdministrador manejadorCrearAdministrador;
    private static ManejadorIniciarSesion manejadorIniciarSesion;
    private static ManejadorCrearCliente manejadorCrearCliente;
    private static ManejadorCrearPublicador manejadorCrearPublicador;
    private static ManejadorSubirArchivo manejadorSubirArchivo;
    private static ManejadorCrearGestorProcesamiento manejadorCrearGestorProcesamiento;
    private static ManejadorProcesarInformacion manejadorProcesarInformacion;


    public static void main(String[] args) {

        Persistencia persistencia = Persistencia.getInstaciaAdministrador();
        persistencia.cargarInfoCSV(RUTA_CSV + DATOS);
        Font font = new Font("Arial", Font.BOLD, 14);

        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("Panel.background", Color.WHITE);
        int opcion;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "1. Crear administrador\n2. Iniciar sesión\n10. Salir",
                        "Menú",
                        JOptionPane.PLAIN_MESSAGE));
            } catch (NumberFormatException exception) {
                opcion = 100;
            }


            switch (opcion) {
                case 1:
                    cargarInfoCrearAdministrador();
                    break;
                case 2:
                    cargarInfoIniciarSesion();
                    break;
                case 10:
                    persistencia.guardarInfoCSV(RUTA_CSV + DATOS);
                    servidorSocket.cerrarConexiones();
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 10);
    }

    public static void cargarInfoCrearAdministrador() {
        manejadorCrearAdministrador = new ManejadorCrearAdministrador();
        String correo = JOptionPane.showInputDialog("Ingrese correo");
        String password = JOptionPane.showInputDialog("Ingrese contraseña");
        TipoUsuario tipo = TipoUsuario.ADMINISTRADOR;

        UsuarioComando usuarioComando = new UsuarioComando(correo, password, tipo);

        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido");
        String identificacion = JOptionPane.showInputDialog("Ingrese identificacion");
        String telefono = JOptionPane.showInputDialog("Ingrese telefono");

        AdministradorComando personaComando = new AdministradorComando(nombre, apellido, identificacion, telefono, usuarioComando);

        try {
            manejadorCrearAdministrador.ejecutar(personaComando);
            JOptionPane.showMessageDialog(null, "Administrador creado con exito!!");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

    }

    public static void cargarInfoIniciarSesion() {
        manejadorIniciarSesion = new ManejadorIniciarSesion();
        String correo = JOptionPane.showInputDialog("Ingrese correo");
        String password = JOptionPane.showInputDialog("Ingrese constraseña");

        try {
            Persona persona = manejadorIniciarSesion.ejecutar(correo, password);

            if (persona instanceof Administrador) {
                if (servidorSocket == null) {
                    servidorSocket = new ServidorSocket();
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        servidorSocket.iniciarSevidor();
                    });
                }
                JOptionPane.showMessageDialog(null, "Administrador, Inicio de sesion con exito!!");
                cargarInfoMenuAdministrador();
            }

            if (persona instanceof Cliente) {
                JOptionPane.showMessageDialog(null, "Cliente, Inicio de sesion con exito!!");
            }

            if (persona instanceof Publicador) {
                JOptionPane.showMessageDialog(null, "Publicador, Inicio de sesion con exito!!");
                cargarInfoMenuPublicador((Publicador) persona);
            }

            if (persona instanceof GestorProcesamiento) {
                JOptionPane.showMessageDialog(null, "Gestor procesador, Inicio de sesion con exito!!");
                cargarInfoMenuGestorProcesamiento();
            }

        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public static void cargarInfoMenuAdministrador() {

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Crear Publicador\n2. Crear Cliente\n3. Crear Gestor Procesamiento\n4. Salir",
                "Menú",
                JOptionPane.PLAIN_MESSAGE));

        switch (opcion) {
            case 1:
                cargarInfoCrearPublicador();
                break;
            case 2:
                cargarInfoCrearCliente();
                break;
            case 3:
                cargarInfoCrearGestorProcesamiento();
                break;
            case 4:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida",
                        "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void cargarInfoCrearCliente() {
        manejadorCrearCliente = new ManejadorCrearCliente();
        String correo = JOptionPane.showInputDialog("Ingrese correo");
        String password = JOptionPane.showInputDialog("Ingrese contraseña");
        TipoUsuario tipo = TipoUsuario.CLIENTE;


        UsuarioComando usuarioComando = new UsuarioComando(correo, password, tipo);

        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido");
        String identificacion = JOptionPane.showInputDialog("Ingrese identificacion");
        String telefono = JOptionPane.showInputDialog("Ingrese telefono");

        ClienteComando clienteComando = new ClienteComando(nombre, apellido, identificacion, telefono, usuarioComando);
        try {
            manejadorCrearCliente.ejecutar(clienteComando);
            JOptionPane.showMessageDialog(null, "Cliente creado");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public static void cargarInfoCrearGestorProcesamiento() {
        manejadorCrearGestorProcesamiento = new ManejadorCrearGestorProcesamiento();
        String correo = JOptionPane.showInputDialog("Ingrese correo");
        String password = JOptionPane.showInputDialog("Ingrese contraseña");
        TipoUsuario tipo = TipoUsuario.GESTOR_PROCESAMIENTO;


        UsuarioComando usuarioComando = new UsuarioComando(correo, password, tipo);

        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido");
        String identificacion = JOptionPane.showInputDialog("Ingrese identificacion");
        String telefono = JOptionPane.showInputDialog("Ingrese telefono");

        GestorProcesamientoComando gestorProcesamientoComando =
                new GestorProcesamientoComando(nombre, apellido, identificacion, telefono, usuarioComando);
        try {
            manejadorCrearGestorProcesamiento.ejecutar(gestorProcesamientoComando);
            JOptionPane.showMessageDialog(null, "Gestor procesamiento creado");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public static void cargarInfoCrearPublicador() {
        manejadorCrearPublicador = new ManejadorCrearPublicador();
        String correo = JOptionPane.showInputDialog("Ingrese correo");
        String password = JOptionPane.showInputDialog("Ingrese contraseña");
        TipoUsuario tipo = TipoUsuario.PUBLICADOR;


        UsuarioComando usuarioComando = new UsuarioComando(correo, password, tipo);

        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido");
        String identificacion = JOptionPane.showInputDialog("Ingrese identificacion");
        String telefono = JOptionPane.showInputDialog("Ingrese telefono");

        PublicadorComando publicadorComando = new PublicadorComando(nombre, apellido, identificacion, telefono, usuarioComando);
        try {
            manejadorCrearPublicador.ejecutar(publicadorComando);
            JOptionPane.showMessageDialog(null, "Publicador creado");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public static void cargarInfoMenuPublicador(Publicador publicador) {
        manejadorSubirArchivo = new ManejadorSubirArchivo();
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Subir Articulo o foto\n3. Salir",
                "Menú",
                JOptionPane.PLAIN_MESSAGE));
        switch (opcion) {
            case 1:
                String rutaArchivo = JOptionPane.showInputDialog("Ingrese la ruta del archivo xml o foto");
                manejadorSubirArchivo.ejecutar(rutaArchivo, publicador);
                break;
            case 3:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida",
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void cargarInfoMenuGestorProcesamiento() {
        manejadorProcesarInformacion = new ManejadorProcesarInformacion();
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "1. Procesar información\n2. Salir",
                "Menú",
                JOptionPane.PLAIN_MESSAGE));
        switch (opcion) {
            case 1:
                manejadorProcesarInformacion.ejecutar();
                break;
            case 2:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida",
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

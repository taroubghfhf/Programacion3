package co.edu.uniquindio.noticias.dominio.servicios.usuario;

import co.edu.uniquindio.noticias.dominio.model.Usuario;

import java.util.Locale;
import java.util.Scanner;

public class UsuarioServicio  {
    private boolean sesionIniciada;
    private Locale idiomaSeleccionado;

    public UsuarioServicio() {
        this.sesionIniciada = false;
        this.idiomaSeleccionado = Locale.getDefault();
    }

    // Método para iniciar sesión
    public void ingresar() {
        if (!sesionIniciada) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Iniciar sesión:");
            // Lógica para el inicio de sesión (ingreso de nombre de usuario, contraseña, etc.)
            // Aquí puedes solicitar la entrada al usuario y realizar la autenticación
            System.out.println("Sesión iniciada correctamente.");
            sesionIniciada = true;
        } else {
            System.out.println("Ya has iniciado sesión.");
        }
    }

    // Método para cerrar sesión
    public void cerrarSesion() {
        if (sesionIniciada) {
            System.out.println("Cerrando sesión...");
            sesionIniciada = false;
            System.out.println("Sesión cerrada correctamente.");
        } else {
            System.out.println("No has iniciado sesión.");
        }
    }

    // Método para seleccionar idioma
    public void seleccionarIdioma() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccionar idioma:");
        System.out.println("1. Español");
        System.out.println("2. Inglés");
        System.out.println("Seleccione el idioma (1 o 2):");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                idiomaSeleccionado = new Locale("es", "ES");
                break;
            case 2:
                idiomaSeleccionado = new Locale("en", "US");
                break;
            default:
                System.out.println("Opción inválida. Se mantendrá el idioma actual.");
        }
        System.out.println("Idioma seleccionado: " + idiomaSeleccionado.getDisplayName());
    }


    }


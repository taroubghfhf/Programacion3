package co.edu.uniquindio.noticias.dominio.servicios.usuario;
import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.model.Usuario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Administrador_Servicio {
    private List<Cliente> clientes;
    private List<Publicador> publicadores;

    public Administrador_Servicio() {
        this.clientes = new ArrayList<>();
        this.publicadores = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente registrado: " + cliente.toString());
    }

    public void registrarPublicador(Publicador publicador) {
        publicadores.add(publicador);
        System.out.println("Publicador registrado: " + publicador.toString());
    }

    public static void guardarPublicador(String nombreArchivo, Publicador publicador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezados (si es necesario)
            writer.write("Nombre,Apellido,Identificacion\n");

          /*  writer.write(publicador.getNombre() + "," +
                    publicador.getApellido() + ",");*/

            System.out.println("Los datos del publicador han sido guardados en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el publicador en el archivo: " + e.getMessage());
        }
    }
}



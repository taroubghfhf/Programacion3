package co.edu.uniquindio.noticias.infaestructura.persistencia;


import co.edu.uniquindio.noticias.dominio.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static Persistencia persistencia;
    private List<Administrador> administradores = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Publicador> publicadors = new ArrayList<>();
    private List<GestorProcesamiento> gestorProcesamientos = new ArrayList<>();

    private Persistencia() {}

    public static Persistencia getInstaciaAdministrador() {
        if (persistencia == null){
            persistencia= new Persistencia();
        }
        return persistencia;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Publicador> getPublicadors() {
        return publicadors;
    }

    public List<GestorProcesamiento> getGestorProcesamientos() {
        return gestorProcesamientos;
    }

    public void guardarInfoCSV(String ruta) {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(administradores);
        personas.addAll(clientes);
        personas.addAll(publicadors);
        personas.addAll(gestorProcesamientos);
        try (PrintWriter writer = new PrintWriter(new File(ruta))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nombre,Apellido,Identificacion,Telefono,Correo,Password,Tipo\n");
            for (Persona persona : personas) {
                sb.append(persona.getNombre()).append(",");
                sb.append(persona.getApellido()).append(",");
                sb.append(persona.getIdentificacion()).append(",");
                sb.append(persona.getTelefono()).append(",");
                sb.append(persona.getUsuario().getCorreo()).append(",");
                sb.append(persona.getUsuario().getPassword()).append(",");
                sb.append(persona.getUsuario().getTipoUsuario().getNombre()).append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {}
    }

    public void cargarInfoCSV(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 7) {
                    String nombre = datos[0];
                    String apellido = datos[1];
                    String identificacion = datos[2];
                    String telefono = datos[3];
                    String correo = datos[4];
                    String password = datos[5];
                    String tipo = datos[6];
                    if (TipoUsuario.ADMINISTRADOR.getNombre().equals(tipo)) {
                        Usuario usuario = new Usuario(correo,password,TipoUsuario.ADMINISTRADOR);
                        administradores.add(new Administrador(nombre,apellido,identificacion,telefono,usuario));
                    } else if (TipoUsuario.CLIENTE.getNombre().equals(tipo)) {
                        Usuario usuario = new Usuario(correo,password,TipoUsuario.CLIENTE);
                        clientes.add(new Cliente(nombre,apellido,identificacion,telefono,usuario));
                    } else if (TipoUsuario.PUBLICADOR.getNombre().equals(tipo)) {
                        Usuario usuario = new Usuario(correo,password,TipoUsuario.PUBLICADOR);
                        publicadors.add(new Publicador(nombre,apellido,identificacion,telefono,usuario));
                    } else if (TipoUsuario.GESTOR_PROCESAMIENTO.getNombre().equals(tipo)){
                        Usuario usuario = new Usuario(correo,password,TipoUsuario.GESTOR_PROCESAMIENTO);
                        gestorProcesamientos.add(new GestorProcesamiento(nombre,apellido,identificacion,telefono,usuario));
                    }
                }
            }
        } catch (IOException e) {}
    }
}

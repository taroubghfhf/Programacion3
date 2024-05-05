package co.edu.uniquindio.noticias.aplicacion.manejador.iniciarsesion;

import co.edu.uniquindio.noticias.dominio.model.Persona;
import co.edu.uniquindio.noticias.dominio.servicios.usuario.IniciarSesionServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.usuario.UsuarioImplementacionDAO;

public class ManejadorIniciarSesion {

    private UsuarioImplementacionDAO usuarioDAO;

    public ManejadorIniciarSesion() {
        this.usuarioDAO = new UsuarioImplementacionDAO();
    }

    public Persona ejecutar(String correo, String password){
        IniciarSesionServicio servicio = new IniciarSesionServicio(usuarioDAO);
        return servicio.ejecutar(correo,password);
    }
}

package co.edu.uniquindio.noticias.aplicacion.fabrica.administrador;

import co.edu.uniquindio.noticias.aplicacion.fabrica.usuario.FabricaUsuario;
import co.edu.uniquindio.noticias.dominio.model.Administrador;
import co.edu.uniquindio.noticias.dominio.model.Usuario;
import co.edu.uniquindio.noticias.aplicacion.comando.AdministradorComando;

public class FabricaAdministrador {

    public static Administrador crear(AdministradorComando administradorComando){
        Usuario usuario = FabricaUsuario.crear(administradorComando.getUsuarioComando());
        return new Administrador(
                administradorComando.getNombre(),
                administradorComando.getApellido(),
                administradorComando.getIdentificacion(),
                administradorComando.getTelefono(),
                usuario);
    }
}

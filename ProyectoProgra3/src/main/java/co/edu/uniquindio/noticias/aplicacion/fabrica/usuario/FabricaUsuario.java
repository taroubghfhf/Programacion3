package co.edu.uniquindio.noticias.aplicacion.fabrica.usuario;

import co.edu.uniquindio.noticias.dominio.model.Usuario;
import co.edu.uniquindio.noticias.aplicacion.comando.UsuarioComando;

public final class FabricaUsuario {

    public static Usuario crear(UsuarioComando usuario){
        return new Usuario(usuario.getCorreo(), usuario.getPassword(), usuario.getTipoUsuario());
    }
}

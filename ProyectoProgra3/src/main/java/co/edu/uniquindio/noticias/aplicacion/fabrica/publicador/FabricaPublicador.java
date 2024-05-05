package co.edu.uniquindio.noticias.aplicacion.fabrica.publicador;

import co.edu.uniquindio.noticias.aplicacion.comando.PublicadorComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.usuario.FabricaUsuario;
import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.model.Usuario;

public class FabricaPublicador {
    public static Publicador crear(PublicadorComando publicadorComando){
        Usuario usuario = FabricaUsuario.crear(publicadorComando.getUsuarioComando());
        return new Publicador(
                publicadorComando.getNombre(),
                publicadorComando.getApellido(),
                publicadorComando.getIdentificacion(),
                publicadorComando.getTelefono(),
                usuario);
    }
}

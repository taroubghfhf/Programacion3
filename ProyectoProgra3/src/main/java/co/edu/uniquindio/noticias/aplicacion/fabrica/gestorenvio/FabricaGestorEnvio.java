package co.edu.uniquindio.noticias.aplicacion.fabrica.gestorenvio;

import co.edu.uniquindio.noticias.aplicacion.comando.GestorEnvioComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.usuario.FabricaUsuario;
import co.edu.uniquindio.noticias.dominio.model.GestorEnvio;
import co.edu.uniquindio.noticias.dominio.model.Usuario;

public class FabricaGestorEnvio {

    public static GestorEnvio crear(GestorEnvioComando gestorEnvioComando){
        Usuario usuario = FabricaUsuario.crear(gestorEnvioComando.getUsuarioComando());
        return new GestorEnvio(
                gestorEnvioComando.getNombre(),
                gestorEnvioComando.getApellido(),
                gestorEnvioComando.getIdentificacion(),
                gestorEnvioComando.getTelefono(),
                usuario);
    }
}

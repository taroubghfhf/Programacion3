package co.edu.uniquindio.noticias.aplicacion.fabrica.gestorprocesamiento;

import co.edu.uniquindio.noticias.aplicacion.comando.GestorProcesamientoComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.usuario.FabricaUsuario;
import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.model.Usuario;

public class FabricaGestorProcesamiento {

    public static GestorProcesamiento crear(GestorProcesamientoComando gestorProcesamientoComando){
        Usuario usuario = FabricaUsuario.crear(gestorProcesamientoComando.getUsuarioComando());
        return new GestorProcesamiento(
                gestorProcesamientoComando.getNombre(),
                gestorProcesamientoComando.getApellido(),
                gestorProcesamientoComando.getIdentificacion(),
                gestorProcesamientoComando.getTelefono(),
                usuario);
    }
}

package co.edu.uniquindio.noticias.aplicacion.fabrica.cliente;

import co.edu.uniquindio.noticias.aplicacion.comando.ClienteComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.usuario.FabricaUsuario;
import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.model.Usuario;

public class FabricaCliente {

    public static Cliente crear(ClienteComando clienteComando){
        Usuario usuario = FabricaUsuario.crear(clienteComando.getUsuarioComando());
        return new Cliente(
                clienteComando.getNombre(),
                clienteComando.getApellido(),
                clienteComando.getIdentificacion(),
                clienteComando.getTelefono(),
                usuario);
    }
}

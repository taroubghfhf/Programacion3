package co.edu.uniquindio.noticias.dominio.servicios.usuario;

import co.edu.uniquindio.noticias.dominio.model.*;


public class Tipo_UsuarioServicio  {



    public boolean esPublicador(Usuario usuario) {
        return usuario != null && usuario.getClass().equals(Publicador.class);
    }

    public boolean esCliente(Usuario usuario) {
        return usuario != null && usuario.getClass().equals(Cliente.class);
    }
}
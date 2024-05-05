package co.edu.uniquindio.noticias.infaestructura.adaptador.publicador;

import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorEnviar;
import co.edu.uniquindio.noticias.infaestructura.socket.ClienteSocket;

public class PublicadorImplementacionEnviar implements PublicadorEnviar {
    private ClienteSocket clienteSocket;
    public PublicadorImplementacionEnviar() {
        this.clienteSocket = new ClienteSocket();
    }

    @Override
    public void enviar(String file,String identificacion) {
        this.clienteSocket.cliente(file, identificacion);
    }
}

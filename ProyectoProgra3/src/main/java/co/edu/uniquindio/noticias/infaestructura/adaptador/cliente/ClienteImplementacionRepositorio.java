package co.edu.uniquindio.noticias.infaestructura.adaptador.cliente;

import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.puerto.cliente.ClienteRepositorio;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

public class ClienteImplementacionRepositorio implements ClienteRepositorio {

    private Persistencia persistencia;

    public ClienteImplementacionRepositorio() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public void crear(Cliente cliente) {
        this.persistencia.getClientes().add(cliente);
    }
}

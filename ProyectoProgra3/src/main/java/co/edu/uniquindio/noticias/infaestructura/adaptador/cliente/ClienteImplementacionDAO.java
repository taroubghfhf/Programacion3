package co.edu.uniquindio.noticias.infaestructura.adaptador.cliente;

import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.puerto.cliente.ClienteDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.List;

public class ClienteImplementacionDAO implements ClienteDAO {

    private Persistencia persistencia;

    public ClienteImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Boolean validarExistencia(String correo) {
        List<Cliente> clientes = persistencia.getClientes();
        return clientes.stream()
                .filter(cliente -> cliente.getUsuario().getCorreo().equals(correo))
                .toList().size()> 0;
    }
}

package co.edu.uniquindio.noticias.aplicacion.manejador.cliente;

import co.edu.uniquindio.noticias.aplicacion.comando.ClienteComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.cliente.FabricaCliente;
import co.edu.uniquindio.noticias.dominio.model.Cliente;
import co.edu.uniquindio.noticias.dominio.servicios.cliente.CrearClienteServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.cliente.ClienteImplementacionDAO;
import co.edu.uniquindio.noticias.infaestructura.adaptador.cliente.ClienteImplementacionRepositorio;

public class ManejadorCrearCliente {

    private ClienteImplementacionDAO clienteImplementacionDAO;
    private ClienteImplementacionRepositorio clienteImplementacionRepositorio;


    public ManejadorCrearCliente() {
        this.clienteImplementacionDAO = new ClienteImplementacionDAO();
        this.clienteImplementacionRepositorio = new ClienteImplementacionRepositorio();
    }

    public void ejecutar(ClienteComando clienteComando){
        Cliente cliente = FabricaCliente.crear(clienteComando);
        CrearClienteServicio servicio = new CrearClienteServicio(clienteImplementacionDAO,
                clienteImplementacionRepositorio);
        servicio.crear(cliente);
    }
}

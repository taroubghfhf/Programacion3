package co.edu.uniquindio.noticias.infaestructura.adaptador.administrador;

import co.edu.uniquindio.noticias.dominio.model.Administrador;
import co.edu.uniquindio.noticias.dominio.puerto.administrador.AdministradorRepositorio;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

public class AdministradorImplementacionRepositorio implements AdministradorRepositorio {

    private Persistencia persistencia;

    public AdministradorImplementacionRepositorio() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public void crear(Administrador administrador) {
        this.persistencia.getAdministradores().add(administrador);
    }
}

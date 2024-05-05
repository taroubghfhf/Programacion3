package co.edu.uniquindio.noticias.infaestructura.adaptador.publicador;

import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorRepositorio;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

public class PublicadorImplementacionRepositorio implements PublicadorRepositorio {
    private Persistencia persistencia;

    public PublicadorImplementacionRepositorio() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public void crear(Publicador publicador) {
        this.persistencia.getPublicadors().add(publicador);
    }
}

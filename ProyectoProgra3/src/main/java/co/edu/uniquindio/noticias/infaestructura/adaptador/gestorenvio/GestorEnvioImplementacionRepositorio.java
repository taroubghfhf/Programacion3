package co.edu.uniquindio.noticias.infaestructura.adaptador.gestorenvio;

import co.edu.uniquindio.noticias.dominio.model.GestorEnvio;
import co.edu.uniquindio.noticias.dominio.puerto.gestorenvio.GestorEnvioRepositorio;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

public class GestorEnvioImplementacionRepositorio  implements GestorEnvioRepositorio {

    private Persistencia persistencia;

    public GestorEnvioImplementacionRepositorio() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public void crear(GestorEnvio gestorEnvio) {
        this.persistencia.getGestorEnvios().add(gestorEnvio);
    }
}

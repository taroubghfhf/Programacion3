package co.edu.uniquindio.noticias.infaestructura.adaptador.gestorenvio;

import co.edu.uniquindio.noticias.dominio.model.GestorEnvio;
import co.edu.uniquindio.noticias.dominio.puerto.gestorenvio.GestorEnvioDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.List;

public class GestorEnvioImplementacionDAO implements GestorEnvioDAO {

    private Persistencia persistencia;

    public GestorEnvioImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Boolean validarExistencia(String correo) {
        List<GestorEnvio> gestorEnvios = persistencia.getGestorEnvios();
        return gestorEnvios.stream()
                .filter(gestor -> gestor.getUsuario().getCorreo().equals(correo))
                .toList().size()> 0;
    }
}

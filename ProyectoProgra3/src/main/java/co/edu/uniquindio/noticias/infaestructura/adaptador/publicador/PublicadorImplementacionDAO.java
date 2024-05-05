package co.edu.uniquindio.noticias.infaestructura.adaptador.publicador;

import co.edu.uniquindio.noticias.dominio.model.Publicador;
import co.edu.uniquindio.noticias.dominio.puerto.publicador.PublicadorDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.List;

public class PublicadorImplementacionDAO implements PublicadorDAO {
    private Persistencia persistencia;

    public PublicadorImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Boolean validarExistencia(String correo) {
        List<Publicador> publicadores = persistencia.getPublicadors();
        return publicadores.stream()
                .filter(publicador -> publicador.getUsuario().getCorreo().equals(correo))
                .toList().size()> 0;
    }
}

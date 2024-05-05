package co.edu.uniquindio.noticias.infaestructura.adaptador.administrador;

import co.edu.uniquindio.noticias.dominio.model.Administrador;
import co.edu.uniquindio.noticias.dominio.puerto.administrador.AdministradorDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.List;

public class AdministradorImplementacionDAO implements AdministradorDAO {

    private Persistencia persistencia;

    public AdministradorImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Boolean validarExistencia(String correo) {
        List<Administrador> administradors = persistencia.getAdministradores();
        return administradors.stream()
                .filter(administrador -> administrador.getUsuario().getCorreo().equals(correo))
                .toList().size()> 0;
    }
}

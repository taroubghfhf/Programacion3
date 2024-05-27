package co.edu.uniquindio.noticias.infaestructura.adaptador.gestorprocesamiento;

import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.puerto.gestorprocesamiento.GestorProcesamientoDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.List;

public class GestorProcesamientoImplementacionDAO implements GestorProcesamientoDAO {

    private Persistencia persistencia;

    public GestorProcesamientoImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Boolean validarExistencia(String correo) {
        List<GestorProcesamiento> gestorProcesamientos = persistencia.getGestorProcesamientos();
        return gestorProcesamientos.stream()
                .filter(gestor -> gestor.getUsuario().getCorreo().equals(correo))
                .toList().size()> 0;
    }
}

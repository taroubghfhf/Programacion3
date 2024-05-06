package co.edu.uniquindio.noticias.dominio.servicios.gestorprocesamiento;

import co.edu.uniquindio.noticias.dominio.exception.YaExisteUsuario;
import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.puerto.gestorprocesamiento.GestorProcesamientoDAO;
import co.edu.uniquindio.noticias.dominio.puerto.gestorprocesamiento.GestorProcesamientoRepositorio;

public class CrearGestorProcesamientoServicio {

    private static final String YA_EXISTE_USUARIO = "El Gestor procesamiento ya existe";
    private static GestorProcesamientoDAO gestorProcesamientoDAO;
    private static GestorProcesamientoRepositorio gestorProcesamientoRepositorio;

    public CrearGestorProcesamientoServicio(GestorProcesamientoDAO gestorProcesamientoDAO,
                                            GestorProcesamientoRepositorio gestorProcesamientoRepositorio ){
      this.gestorProcesamientoDAO = gestorProcesamientoDAO;
      this.gestorProcesamientoRepositorio = gestorProcesamientoRepositorio;
    }

    public void crear(GestorProcesamiento gestorProcesamiento) {
        if (gestorProcesamientoDAO.validarExistencia(gestorProcesamiento.getUsuario().getCorreo())) {
            throw new YaExisteUsuario(YA_EXISTE_USUARIO);
        }
        this.gestorProcesamientoRepositorio.crear(gestorProcesamiento);
    }
}

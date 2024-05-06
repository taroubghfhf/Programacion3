package co.edu.uniquindio.noticias.infaestructura.adaptador.gestorprocesamiento;

import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.puerto.gestorprocesamiento.GestorProcesamientoRepositorio;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

public class GestorProcesamientoImplementacionRepositorio implements GestorProcesamientoRepositorio {
    private Persistencia persistencia;

    public GestorProcesamientoImplementacionRepositorio() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public void crear(GestorProcesamiento gestorProcesamiento) {
        this.persistencia.getGestorProcesamientos().add(gestorProcesamiento);
    }
}

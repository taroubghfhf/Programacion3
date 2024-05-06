package co.edu.uniquindio.noticias.aplicacion.manejador.gestorprocesamiento;

import co.edu.uniquindio.noticias.aplicacion.comando.GestorProcesamientoComando;
import co.edu.uniquindio.noticias.aplicacion.fabrica.gestorprocesamiento.FabricaGestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import co.edu.uniquindio.noticias.dominio.servicios.gestorprocesamiento.CrearGestorProcesamientoServicio;
import co.edu.uniquindio.noticias.infaestructura.adaptador.gestorprocesamiento.GestorProcesamientoImplementacionDAO;
import co.edu.uniquindio.noticias.infaestructura.adaptador.gestorprocesamiento.GestorProcesamientoImplementacionRepositorio;

public class ManejadorCrearGestorProcesamiento {

   private GestorProcesamientoImplementacionDAO gestorProcesamientoImplementacionDAO;
   private GestorProcesamientoImplementacionRepositorio gestorProcesamientoImplementacionRepositorio;

    public ManejadorCrearGestorProcesamiento() {
        this.gestorProcesamientoImplementacionDAO = new GestorProcesamientoImplementacionDAO();
        this.gestorProcesamientoImplementacionRepositorio = new GestorProcesamientoImplementacionRepositorio();
    }

    public void ejecutar(GestorProcesamientoComando gestorProcesamientoComando){
        GestorProcesamiento gestorProcesamiento = FabricaGestorProcesamiento.crear(gestorProcesamientoComando);
        CrearGestorProcesamientoServicio crearGestorProcesamientoServicio =
                new CrearGestorProcesamientoServicio(gestorProcesamientoImplementacionDAO, gestorProcesamientoImplementacionRepositorio);
        crearGestorProcesamientoServicio.crear(gestorProcesamiento);
    }
}

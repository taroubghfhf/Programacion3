package co.edu.uniquindio.noticias.dominio.exception;

public class YaExisteUsuario extends RuntimeException{

    public YaExisteUsuario(String message) {
        super(message);
    }
}

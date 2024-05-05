package co.edu.uniquindio.noticias.utils.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    public static void validadorNoNull(Object object,String mensajeExcepcion){
        if(object == null){
            throw new RuntimeException(mensajeExcepcion);
        }
    }

    public static void validadorCorreo(String correo, String mensajeExcepcion){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        if (!matcher.matches()) {
            throw new RuntimeException(mensajeExcepcion);
        }
    }

    public static void validarNumero(String numero, String mensajeExcepcion){
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numero);

        if (!matcher.matches()) {
            throw new RuntimeException(mensajeExcepcion);
        }
    }
}

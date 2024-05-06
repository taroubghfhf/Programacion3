package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static co.edu.uniquindio.noticias.infaestructura.utils.validaciones.Validador.validadorNoNull;
import static co.edu.uniquindio.noticias.infaestructura.utils.validaciones.Validador.validarNumero;

@Getter
@NoArgsConstructor
public class Persona {
    private static final String CAMPO_NOMBRE_OBLIGATORIO= "Debe ingresar un nombre";
    private static final String CAMPO_APELLIDO_OBLIGATORIO= "Debe ingresar un apellido";
    private static final String CAMPO_IDENTIFICACION_OBLIGATORIO= "Debe ingresar numero de identificación sin espacios ni coma";
    private static final String CAMPO_TELEFONO_OBLIGATORIO= "Debe ingresar un numero de telefono";
    private static final String CAMPO_USUARIO_OBLIGATORIO= "Debe ingresar la informacion del usuario";
    private static final String CAMPO_TELEFONO_INVALIDO= "Debe ingresar solo numeros en el campo telefono";
    private static final String CAMPO_IDENTIFICACION_INVALIDO= "Debe ingresar solo numeros en el campo identificacion";

    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private Usuario usuario;

    public Persona(String nombre, String apellido, String identificacion, String telefono, Usuario usuario) {
        validadorNoNull(nombre,CAMPO_NOMBRE_OBLIGATORIO);
        validadorNoNull(apellido, CAMPO_APELLIDO_OBLIGATORIO);
        validadorNoNull(identificacion,CAMPO_IDENTIFICACION_OBLIGATORIO);
        validadorNoNull(telefono,CAMPO_TELEFONO_OBLIGATORIO);
        validadorNoNull(usuario,CAMPO_USUARIO_OBLIGATORIO);
        validarNumero(identificacion,CAMPO_TELEFONO_INVALIDO);
        validarNumero(telefono,CAMPO_IDENTIFICACION_INVALIDO);
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.usuario = usuario;
    }
}
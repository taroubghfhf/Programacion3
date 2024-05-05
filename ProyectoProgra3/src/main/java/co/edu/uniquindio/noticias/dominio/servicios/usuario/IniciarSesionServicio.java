package co.edu.uniquindio.noticias.dominio.servicios.usuario;

import co.edu.uniquindio.noticias.dominio.exception.UsuarioInvalidoException;
import co.edu.uniquindio.noticias.dominio.model.Persona;
import co.edu.uniquindio.noticias.dominio.puerto.usuario.UsuarioDAO;

import java.util.Optional;

import static co.edu.uniquindio.noticias.utils.validaciones.Validador.validadorCorreo;

public class IniciarSesionServicio {
    private static final String CORREO_INVALIDO = "Debe ingresar un correo valido";
    private static final String USUARIO_INVALIDO = "Usuario o contraseña invalida";
    private UsuarioDAO usuarioDAO;

    public IniciarSesionServicio(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Persona ejecutar(String correo, String password){
        validadorCorreo(correo, CORREO_INVALIDO);
        Optional<Persona> persona = usuarioDAO.validarSesion(correo,password);
        if(!persona.isPresent()){
            throw new UsuarioInvalidoException(USUARIO_INVALIDO);
        }

        return persona.get();
    }
}

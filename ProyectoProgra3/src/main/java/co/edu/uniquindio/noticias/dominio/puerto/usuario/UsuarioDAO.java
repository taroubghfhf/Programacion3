package co.edu.uniquindio.noticias.dominio.puerto.usuario;

import co.edu.uniquindio.noticias.dominio.model.Persona;

import java.util.Optional;

public interface UsuarioDAO {
    Optional<Persona> validarSesion(String correo, String password);
}

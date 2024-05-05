package co.edu.uniquindio.noticias.infaestructura.adaptador.usuario;

import co.edu.uniquindio.noticias.dominio.model.Persona;
import co.edu.uniquindio.noticias.dominio.puerto.usuario.UsuarioDAO;
import co.edu.uniquindio.noticias.infaestructura.persistencia.Persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioImplementacionDAO implements UsuarioDAO {

    private Persistencia persistencia;

    public UsuarioImplementacionDAO() {
        this.persistencia = Persistencia.getInstaciaAdministrador();
    }

    @Override
    public Optional<Persona> validarSesion(String correo, String password) {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(persistencia.getAdministradores());
        personas.addAll(persistencia.getClientes());
        personas.addAll(persistencia.getPublicadors());


        Optional<Persona> personaEncontrada = personas.stream()
                .filter(persona ->
                        persona.getUsuario().getCorreo().equals(correo)
                                && persona.getUsuario().getPassword().equals(password))
                .findFirst();

        return personaEncontrada;
    }
}

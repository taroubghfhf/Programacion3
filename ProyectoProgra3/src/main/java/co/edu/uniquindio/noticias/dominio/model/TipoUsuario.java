package co.edu.uniquindio.noticias.dominio.model;

import lombok.Getter;

@Getter
public enum TipoUsuario {
    CLIENTE("cliente"),
    GESTOR_ENVIO("gestor_envio"),
    GESTOR_PROCESAMIENTO("gestor_procesamiento"),
    PUBLICADOR("publicador"),
    ADMINISTRADOR("administrador");
    private String nombre;

    TipoUsuario(String nombre){
        this.nombre=nombre;
    }
}
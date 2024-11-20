package br.com.fiap.service;

public final class UsuarioServiceFactory {
    private UsuarioServiceFactory() {

    }

    public static UsuarioService create() {
        return new UsuarioServiceImpl();
    }
}

package br.com.fiap.daos;

public final class UsuarioDaoFactory {

    private UsuarioDaoFactory() {
    }

    public static UsuarioDao create() {
        return new UsuarioDaoImpl();
    }
}

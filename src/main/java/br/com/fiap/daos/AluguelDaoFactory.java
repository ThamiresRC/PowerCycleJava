package br.com.fiap.daos;

public final class AluguelDaoFactory {

    private AluguelDaoFactory() {
    }

    public static AluguelDao create() {
        return new AluguelDaoImpl();
    }
}

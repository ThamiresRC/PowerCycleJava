package br.com.fiap.daos;

public final class BicicletaDaoFactory {

    private BicicletaDaoFactory() {
    }

    public static BicicletaDao create() {
        return new BicicletaDaoImpl();
    }
}

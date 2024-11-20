package br.com.fiap.daos;

public final class EnergiaDaoFactory {

    private EnergiaDaoFactory() {
    }

    public static EnergiaDao create() {
        return new EnergiaDaoImpl();
    }
}

package br.com.fiap.service;

public final class BicicletaServiceFactory {

    private BicicletaServiceFactory() {
        throw new UnsupportedOperationException("Classe de fábrica não instanciável.");
    }

    public static BicicletaService create() {
        return new BicicletaServiceImpl();
    }
}

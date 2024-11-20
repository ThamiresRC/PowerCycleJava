package br.com.fiap.service;

public final class AluguelServiceFactory {
    private AluguelServiceFactory() {

    }

    public static AluguelService create() {
        return new AluguelServiceImpl();
    }
}

package br.com.fiap.service;

public class EnergiaServiceFactory {
    private EnergiaServiceFactory(){

    }

    public static EnergiaService create() {
        return new EnergiaServiceImpl();
    }
}

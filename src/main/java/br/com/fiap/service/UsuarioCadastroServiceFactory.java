package br.com.fiap.service;

public class UsuarioCadastroServiceFactory {

    private UsuarioCadastroServiceFactory() {

    }
    public static UsuarioCadastroService create() {
        return new UsuarioCadastroServiceImpl();
    }
}

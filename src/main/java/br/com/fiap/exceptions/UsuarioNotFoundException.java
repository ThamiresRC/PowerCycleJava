package br.com.fiap.exceptions;

public class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException(int id) {
        super("Usuário com ID " + id + " não foi encontrado.");
    }
}

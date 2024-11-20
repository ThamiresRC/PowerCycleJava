package br.com.fiap.exceptions;

public class AluguelNotFound extends Exception {

    public AluguelNotFound(int id) {
        super("Aluguel com ID " + id + " não encontrado.");
    }

    public AluguelNotFound(String message) {
        super(message);
    }

    public AluguelNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AluguelNotFound(Throwable cause) {
        super(cause);
    }
}

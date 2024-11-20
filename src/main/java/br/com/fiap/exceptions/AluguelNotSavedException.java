package br.com.fiap.exceptions;
public class AluguelNotSavedException extends Exception {

    public AluguelNotSavedException() {
        super("Erro ao salvar o aluguel.");
    }

    public AluguelNotSavedException(String message) {
        super(message);
    }

    public AluguelNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AluguelNotSavedException(Throwable cause) {
        super(cause);
    }
}

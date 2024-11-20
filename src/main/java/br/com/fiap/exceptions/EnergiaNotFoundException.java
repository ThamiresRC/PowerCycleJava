package br.com.fiap.exceptions;

public class EnergiaNotFoundException extends Exception {
    public EnergiaNotFoundException(String message) {
        super(message);
    }

    public EnergiaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

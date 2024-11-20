package br.com.fiap.exceptions;

public class AluguelServiceException extends Exception {

    public AluguelServiceException(String message) {
        super(message);
    }

    public AluguelServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AluguelServiceException(Throwable cause) {
        super(cause);
    }
}


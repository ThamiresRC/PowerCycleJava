package br.com.fiap.exceptions;

public class BicicletaNaoDisponivelException extends RuntimeException {

  public BicicletaNaoDisponivelException() {
    super();
  }

  public BicicletaNaoDisponivelException(String message) {
    super(message);
  }

  public BicicletaNaoDisponivelException(String message, Throwable cause) {
    super(message, cause);
  }
}

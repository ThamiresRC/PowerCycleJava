package br.com.fiap.exceptions;

public class RelatorioNotFound extends RuntimeException {
  public RelatorioNotFound(String message) {
    super(message);
  }
}

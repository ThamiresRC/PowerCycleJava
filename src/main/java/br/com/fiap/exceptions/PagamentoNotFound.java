package br.com.fiap.exceptions;

public class PagamentoNotFound extends RuntimeException {
  public PagamentoNotFound(String message) {
    super(message);
  }
}

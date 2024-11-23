package br.com.fiap.exceptions;

public class PagamentoNotSavedException extends RuntimeException {
  public PagamentoNotSavedException(String message) {
    super(message);
  }
}

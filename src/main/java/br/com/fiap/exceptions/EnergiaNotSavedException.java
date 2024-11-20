package br.com.fiap.exceptions;

public class EnergiaNotSavedException extends Exception {
  public EnergiaNotSavedException(String message) {
    super(message);
  }

  public EnergiaNotSavedException(String message, Throwable cause) {
    super(message, cause);
  }
}

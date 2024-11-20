package br.com.fiap.exceptions;

public class EnergiaServiceException extends Exception {
  public EnergiaServiceException(String message) {
    super(message);
  }

  public EnergiaServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

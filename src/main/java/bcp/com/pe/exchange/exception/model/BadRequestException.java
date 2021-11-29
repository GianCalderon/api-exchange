package bcp.com.pe.exchange.exception.model;

import lombok.Getter;

public class BadRequestException extends RuntimeException {

  @Getter
  private final String code;

  public BadRequestException(String code, String message) {
    super(message);
    this.code = code;


  }
}

package bcp.com.pe.exchange.exception.model;

import lombok.Getter;

public class BusinessChangeException extends RuntimeException{

  @Getter
  private final String code;

  public BusinessChangeException(String code, String message) {
    super(message);
    this.code = code;


  }
}

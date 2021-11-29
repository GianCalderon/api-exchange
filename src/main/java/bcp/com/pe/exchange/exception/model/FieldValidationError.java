package bcp.com.pe.exchange.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldValidationError {

  private String field;
  private String error;

}

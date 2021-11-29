package bcp.com.pe.exchange.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {

  String code;
  String message;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  List<FieldValidationError> fieldErrors;
}

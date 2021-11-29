package bcp.com.pe.exchange.exception;

import bcp.com.pe.exchange.exception.model.BadRequestException;
import bcp.com.pe.exchange.exception.model.BusinessChangeException;
import bcp.com.pe.exchange.exception.model.Error;
import bcp.com.pe.exchange.exception.model.FieldValidationError;
import bcp.com.pe.exchange.exception.model.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {


  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Error> handleTransferException(
      AuthenticationException authenticationException) {
    Error apiResponse = Error.builder()
        .code("E01")
        .message("Credenciales invalidas")
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Error> handleTransferException(
      MissingServletRequestParameterException missingServletRequestParameterException) {
    Error apiResponse = Error.builder()
        .code("E02")
        .message(missingServletRequestParameterException.getMessage())
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<Error> handleTransferException(ExpiredJwtException expiredJwtException) {
    Error apiResponse = Error.builder()
        .code("E06")
        .message("Token de Autorizacion Expirado")
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Error> handleTransferException(UnauthorizedException unauthorizedException) {
    Error apiResponse = Error.builder()
        .code("E05")
        .message("No Autorizado para consumir el servicio")
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BusinessChangeException.class)
  public ResponseEntity<Error> handleTransferException(BusinessChangeException businessChangeException) {
    log.info("Operacion fallida por {} - {}", businessChangeException.getCode(), businessChangeException.getMessage());
    Error apiResponse = Error.builder()
        .code(businessChangeException.getCode())
        .message(businessChangeException.getMessage())
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Error> handleTransferException(BadRequestException badRequestException) {
    log.info("Validacion fallida por {} - {}", badRequestException.getCode(), badRequestException.getMessage());
    Error apiResponse = Error.builder()
        .code(badRequestException.getCode())
        .message(badRequestException.getMessage())
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Error> handleValidations(MethodArgumentNotValidException validationException) {

    List<FieldValidationError> fieldValidationErrors = validationException.getBindingResult().getFieldErrors()
        .stream()
        .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
        .collect(Collectors.toList());

    Error apiResponse = Error.builder()
        .code("E02")
        .message("Error de validación")
        .fieldErrors(fieldValidationErrors)
        .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
}

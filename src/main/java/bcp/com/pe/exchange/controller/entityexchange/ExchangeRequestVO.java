package bcp.com.pe.exchange.controller.entityexchange;

import java.math.BigDecimal;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ExchangeRequestVO {

  @Positive(message = "Valor debe ser mayor a 0")
  private BigDecimal originAmount;
  @Length(min = 3, max = 3, message = " Valor debe debe contener solo 3 caracteres")
  private String originCurrency;
  @Length(min = 3, max = 3, message = " Valor debe debe contener solo 3 caracteres")
  private String chargeCurrency;

}

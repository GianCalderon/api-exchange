package bcp.com.pe.exchange.controller.entityexchange;

import java.math.BigDecimal;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class SaveExchangeRequestVO {


  @Length(min = 3, max = 3, message = " Valor debe debe contener solo 3 caracteres")
  private String originCurrency;
  @Length(min = 3, max = 3, message = " Valor debe debe contener solo 3 caracteres")
  private String destinyCurrency;
  @Positive(message = "Valor debe ser mayor a 0")
  private BigDecimal rate;
}

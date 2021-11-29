package bcp.com.pe.exchange.controller.entityexchange;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaveExchangeResponseVO {

  private Long id;
  private String originCurrency;
  private String destinyCurrency;
  BigDecimal rate;
}

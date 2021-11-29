package bcp.com.pe.exchange.controller;

import bcp.com.pe.exchange.common.anotation.JwtAction;
import bcp.com.pe.exchange.controller.entityexchange.ExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.ExchangeResponseVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeResponseVO;
import bcp.com.pe.exchange.service.IExchangeService;
import io.reactivex.Single;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class ExchangeController {

  private final IExchangeService exchangeService;

  @JwtAction("client")
  @PostMapping("/type-exchange")
  public Single<ResponseEntity<ExchangeResponseVO>> exchange(@Valid @RequestBody ExchangeRequestVO exchangeRequestVo) {
    return exchangeService.changeAmount(exchangeRequestVo);
  }

  @JwtAction("admin")
  @PostMapping("/save-exchange")
  public Single<ResponseEntity<SaveExchangeResponseVO>> saveExchange(
      @Valid @RequestBody SaveExchangeRequestVO saveExchangeRequestVO) {
    return exchangeService.saveExchange(saveExchangeRequestVO);
  }

}

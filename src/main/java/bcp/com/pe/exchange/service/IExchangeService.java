package bcp.com.pe.exchange.service;

import bcp.com.pe.exchange.controller.entityexchange.ExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.ExchangeResponseVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeResponseVO;
import io.reactivex.Single;
import org.springframework.http.ResponseEntity;

public interface IExchangeService {

  Single<ResponseEntity<ExchangeResponseVO>>  changeAmount(ExchangeRequestVO exchangeRequestVo);

  Single<ResponseEntity<SaveExchangeResponseVO>> saveExchange(SaveExchangeRequestVO saveExchangeRequestVO);
}

package bcp.com.pe.exchange.service;

import bcp.com.pe.exchange.controller.entityexchange.ExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.ExchangeResponseVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeResponseVO;
import bcp.com.pe.exchange.exception.model.BadRequestException;
import bcp.com.pe.exchange.exception.model.BusinessChangeException;
import bcp.com.pe.exchange.repository.IExchangeRepository;
import bcp.com.pe.exchange.service.map.MapperService;
import io.reactivex.Single;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements IExchangeService {

  private final IExchangeRepository exchangeRepository;
  private final MapperService mapperService;

  @Override
  public Single<ResponseEntity<SaveExchangeResponseVO>> saveExchange(SaveExchangeRequestVO saveExchangeRequestVO) {
    return Single
        .just(exchangeRepository.save(mapperService.mapToExchangeDto(validationSaveExchangeRequest(saveExchangeRequestVO))))
        .map(data -> ResponseEntity.status(HttpStatus.CREATED).body(mapperService.mapToSaveExchangeResponseVO(data)));
  }

  @Override
  public Single<ResponseEntity<ExchangeResponseVO>> changeAmount(ExchangeRequestVO exchangeRequestVo) {

    return exchangeRepository.findByOriginCurrencyAndDestinyCurrency(exchangeRequestVo.getOriginCurrency(),
        exchangeRequestVo.getChargeCurrency()).map(exchange -> {
      return Single.just(ResponseEntity.status(HttpStatus.OK)
          .body(ExchangeResponseVO.builder()
              .changedAmount(exchange.getRate().multiply(exchangeRequestVo.getOriginAmount())
                  .setScale(3, RoundingMode.HALF_UP))
              .currency(exchange.getDestinyCurrency())
              .build()));
    }).orElse(Single.error(new BusinessChangeException("03", "Tipo de cambio no soportado")));
  }

  private static SaveExchangeRequestVO validationSaveExchangeRequest(SaveExchangeRequestVO saveExchangeRequestVO) {
    if (saveExchangeRequestVO.getOriginCurrency().equals(saveExchangeRequestVO.getDestinyCurrency())) {
      throw new BadRequestException("E02", "Tipo Moneda Origen no debe ser igual al tipo moneda de cambio");
    }
    return saveExchangeRequestVO;
  }

}


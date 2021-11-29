package bcp.com.pe.exchange.service.map;

import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeRequestVO;
import bcp.com.pe.exchange.controller.entityexchange.SaveExchangeResponseVO;
import bcp.com.pe.exchange.controller.entityuser.UserReponse;
import bcp.com.pe.exchange.controller.entityuser.UserRequest;
import bcp.com.pe.exchange.repository.dto.ExchangeDto;
import bcp.com.pe.exchange.repository.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class MapperService {

  public SaveExchangeResponseVO mapToSaveExchangeResponseVO(ExchangeDto exchangeDto) {

    return SaveExchangeResponseVO.builder()
        .id(exchangeDto.getId())
        .originCurrency(exchangeDto.getOriginCurrency())
        .destinyCurrency(exchangeDto.getDestinyCurrency())
        .rate(exchangeDto.getRate())
        .build();
  }

  public ExchangeDto mapToExchangeDto(SaveExchangeRequestVO saveExchangeRequestVO) {

    ExchangeDto exchangeDto = new ExchangeDto();

    exchangeDto.setOriginCurrency(saveExchangeRequestVO.getOriginCurrency());
    exchangeDto.setDestinyCurrency(saveExchangeRequestVO.getDestinyCurrency());
    exchangeDto.setRate(saveExchangeRequestVO.getRate());
    return exchangeDto;
  }

  public UserDto mapToUserDto(UserRequest userRequest) {
    UserDto userDto = new UserDto();
    userDto.setUserName(userRequest.getUserName());
    userDto.setPassword(userRequest.getPassword());
    userDto.setRol(userRequest.getRol());
    return userDto;
  }

  public UserReponse mapToUserReponse(UserDto userDto) {
    return UserReponse.builder()
        .id(userDto.getId())
        .userName(userDto.getUserName())
        .password(userDto.getPassword())
        .rol(userDto.getRol())
        .build();

  }

}

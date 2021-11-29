package bcp.com.pe.exchange.repository;

import bcp.com.pe.exchange.repository.dto.ExchangeDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExchangeRepository extends JpaRepository<ExchangeDto, Long> {

  Optional<ExchangeDto> findByOriginCurrencyAndDestinyCurrency(
      String originCurrency, String destinyCurrency);
}

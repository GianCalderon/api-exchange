package bcp.com.pe.exchange.controller.entityuser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
  String token;
}

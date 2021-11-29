package bcp.com.pe.exchange.controller.entityuser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserReponse {

  private long id;
  private String userName;
  private String password;
  private String rol;

}

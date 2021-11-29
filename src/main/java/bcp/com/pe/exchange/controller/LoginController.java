package bcp.com.pe.exchange.controller;

import bcp.com.pe.exchange.controller.entityuser.LoginResponse;
import bcp.com.pe.exchange.controller.entityuser.UserReponse;
import bcp.com.pe.exchange.controller.entityuser.UserRequest;
import bcp.com.pe.exchange.exception.model.UnauthorizedException;
import bcp.com.pe.exchange.security.JwtService;
import bcp.com.pe.exchange.service.UserImpl;
import io.reactivex.Single;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LoginController {

  private final JwtService jwtService;
  private final UserImpl userImpl;

  @GetMapping("/api/generate-token")
  public Single<LoginResponse> generateToken(@RequestParam("user") String userName,
                                     @RequestParam("password") String password) throws AuthenticationException {
    return jwtService.generateToken(userName, password);
  }

  @PostMapping("/api/user-login")
  public Single<UserReponse> saveUser(@RequestBody UserRequest userRequest) {
    return userImpl.saveUserLogin(userRequest);
  }
}

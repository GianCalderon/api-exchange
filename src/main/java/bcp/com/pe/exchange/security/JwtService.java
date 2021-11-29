package bcp.com.pe.exchange.security;

import bcp.com.pe.exchange.controller.entityuser.LoginResponse;
import bcp.com.pe.exchange.repository.IUserRepository;
import bcp.com.pe.exchange.repository.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.reactivex.Single;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final IUserRepository iUserRepository;
  private static final long JWT_DURATION = TimeUnit.SECONDS.toMillis(60);
  private static final String JWT_SIGNATURE_SECRET = "mifirma";
  private static final String CLAIM_PERMISSIONS = "permits";
  private static final String CLAIM_USER = "user";
  private static final String CLAIM_PASSWORD = "password";
  private static final String CLAIM_ROL = "rol";



  /*
  public String generateToken(String permisions) {

    Date currentDate = new Date();
    long endTime = currentDate.getTime() + JWT_DURATION;
    return Jwts.builder()
        .setIssuedAt(currentDate)
        .setExpiration(new Date(endTime))
        .signWith(SignatureAlgorithm.HS256, JWT_SIGNATURE_SECRET)
        .claim(CLAIM_PERMISSIONS, permisions)
        .compact();
  }
   */

  public Single<LoginResponse> generateToken(String userName, String password) throws AuthenticationException {

    Optional<UserDto> userDto = Optional.ofNullable(
        iUserRepository.findByUserNameAndPassword(userName, password).orElseThrow(AuthenticationException::new));
    String rolClain = userDto.get().getRol();

    Date currentDate = new Date();
    long endTime = currentDate.getTime() + JWT_DURATION;
    String token = Jwts.builder()
        .setIssuedAt(currentDate)
        .setExpiration(new Date(endTime))
        .signWith(SignatureAlgorithm.HS256, JWT_SIGNATURE_SECRET)
        .claim(CLAIM_ROL, rolClain)
        .compact();

    return Single.just(LoginResponse.builder().token(token).build());
  }

  boolean validateToken(String token, String rolToValidate) {

    Jws<Claims> parsedJwt = Jwts.parser()
        .setSigningKey(JWT_SIGNATURE_SECRET)
        .parseClaimsJws(token);

    String rol = parsedJwt.getBody().get(CLAIM_ROL, String.class);
    return Arrays.stream(rol.split("-")).anyMatch(e -> e.equals(rolToValidate));
  }


}

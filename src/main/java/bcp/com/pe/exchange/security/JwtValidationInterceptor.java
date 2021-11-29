package bcp.com.pe.exchange.security;

import bcp.com.pe.exchange.common.anotation.JwtAction;
import bcp.com.pe.exchange.exception.model.UnauthorizedException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtValidationInterceptor implements HandlerInterceptor {

  private final JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      JwtAction jwtAction = method.getAnnotation(JwtAction.class);
      if (jwtAction == null) {
        return true;
      }

      String authorization = request.getHeader("Authorization");

      if (authorization == null || !authorization.startsWith("Bearer ")) {
        throw new UnauthorizedException();
      }
      String jwt = authorization.substring(7);
      log.info("Token,{}", jwt);
      log.info("JwtAction,{}", jwtAction.value());

      boolean isAuhtorized = jwtService.validateToken(jwt, jwtAction.value());
      if (!isAuhtorized) {
        throw new UnauthorizedException();

      }

    }
    return true;
  }
}

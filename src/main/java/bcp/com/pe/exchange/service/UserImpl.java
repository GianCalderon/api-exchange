package bcp.com.pe.exchange.service;

import bcp.com.pe.exchange.controller.entityuser.UserReponse;
import bcp.com.pe.exchange.controller.entityuser.UserRequest;
import bcp.com.pe.exchange.repository.IUserRepository;
import bcp.com.pe.exchange.service.map.MapperService;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

  private final IUserRepository userRepository;
  private final MapperService mapperService;

  @Override
  public Single<UserReponse> saveUserLogin(UserRequest userRequest) {
    return Single.just(mapperService.mapToUserReponse(userRepository.save(mapperService.mapToUserDto(userRequest))));
  }
}

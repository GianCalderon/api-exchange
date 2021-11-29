package bcp.com.pe.exchange.service;

import bcp.com.pe.exchange.controller.entityuser.UserReponse;
import bcp.com.pe.exchange.controller.entityuser.UserRequest;
import io.reactivex.Single;

public interface UserService {

  Single<UserReponse> saveUserLogin(UserRequest userRequest);
}

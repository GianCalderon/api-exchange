package bcp.com.pe.exchange.repository;

import bcp.com.pe.exchange.repository.dto.UserDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserDto, Long> {
  Optional<UserDto> findByUserNameAndPassword(String user, String password);

}

package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.UserEntity;
import bg.softuni.cars.repositories.UserRepository;
import bg.softuni.cars.services.UserService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserServiceImpl(PasswordEncoder passwordEncoder,
      UserRepository userRepository) {

    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public boolean isLoginValid(String userName, String password) {
    Optional<UserEntity> userEntity = userRepository.findByUserName(userName);

    return userEntity.
        map(UserEntity::getPassword).
        filter(pwd -> pwd.equals(passwordEncoder.encode(password))).
        isPresent();
  }
}

package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.UserEntity;
import bg.softuni.cars.repositories.UserRepository;
import bg.softuni.cars.security.CurrentUser;
import bg.softuni.cars.services.UserService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final CurrentUser currentUser;

  public UserServiceImpl(PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      CurrentUser currentUser) {

    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.currentUser = currentUser;
  }

  @Override
  public boolean isLoginValid(String userName, String password) {
    Optional<UserEntity> userEntity = userRepository.findByUserName(userName);

    return userEntity.
        map(UserEntity::getPassword).
        filter(pwd -> passwordEncoder.matches(password, pwd)).
        isPresent();
  }

  @Override
  public void loginUser(String userName) {
    currentUser.
        setUserName(userName).
        setAnonymous(false);
  }

  @Override
  public void logoutUser() {
    currentUser.
        setAnonymous(true);
  }
}

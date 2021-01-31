package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.UserEntity;
import bg.softuni.cars.models.entities.UserRoleEntity;
import bg.softuni.cars.models.entities.enums.UserRoleEnum;
import bg.softuni.cars.repositories.UserRepository;
import bg.softuni.cars.security.CurrentUser;
import bg.softuni.cars.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    UserEntity userEntity = userRepository.
        findByUserName(userName).
        orElseThrow(() -> new IllegalArgumentException("User with name " + userName + " not found!"));

    List<UserRoleEnum> userRoles = userEntity.
        getUserRoles().
        stream().
        map(UserRoleEntity::getUserRole).
        collect(Collectors.toList());

    currentUser.
        setUserName(userEntity.getUserName()).
        addUserRoles(userRoles).
        setAnonymous(false);
  }

  @Override
  public void logoutUser() {
    currentUser.
        setAnonymous(true);
  }
}

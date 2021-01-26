package bg.softuni.cars.services.impl;

import bg.softuni.cars.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public boolean isLoginValid(String userName, String password) {
    return true;
  }
}

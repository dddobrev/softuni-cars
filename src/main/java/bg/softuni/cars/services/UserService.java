package bg.softuni.cars.services;

public interface UserService {

  boolean isLoginValid(String userName, String password);

  void loginUser(String userName);

  void logoutUser();

}

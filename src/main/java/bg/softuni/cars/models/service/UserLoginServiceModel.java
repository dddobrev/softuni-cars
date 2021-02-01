package bg.softuni.cars.models.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserLoginServiceModel {

  @NotNull
  @Size(min = 2, message = "Username must be more than 2 characters")
  private String username;

  @NotNull
  @Size(min = 2, message = "Password must be more than 3 characters")
  private String password;

  public String getUsername() {
    return username;
  }

  public UserLoginServiceModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserLoginServiceModel setPassword(String password) {
    this.password = password;
    return this;
  }
}

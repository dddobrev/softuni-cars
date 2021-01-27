package bg.softuni.cars.web;

import bg.softuni.cars.models.service.UserLoginServiceModel;
import bg.softuni.cars.security.CurrentUser;
import bg.softuni.cars.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  private final CurrentUser currentUser;
  private final UserService userService;

  public LoginController(CurrentUser currentUser,
      UserService userService) {
    this.currentUser = currentUser;
    this.userService = userService;
  }

  @GetMapping("/users/logout")
  public String logout() {
    currentUser.setAnonymous(true);
    return "index";
  }

  @GetMapping("/users/login")
  public String showLogin() {
    return "auth-login";
  }

  @PostMapping("/users/login")
  public String login(UserLoginServiceModel userLoginServiceModel) {

    if (userService.isLoginValid(userLoginServiceModel.getUsername(),
        userLoginServiceModel.getPassword())) {
      currentUser.
          setUserName(userLoginServiceModel.getUsername()).
          setAnonymous(false);
    }

    return "redirect:/";
  }

}

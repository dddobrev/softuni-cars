package bg.softuni.cars.web;

import bg.softuni.cars.models.service.UserLoginServiceModel;
import bg.softuni.cars.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/logout")
  public String logout() {
    userService.logoutUser();
    return "index";
  }

  @GetMapping("/users/login")
  public String showLogin(Model model) {
    if (!model.containsAttribute("userModel")) {
      model.addAttribute("userModel", new UserLoginServiceModel());
      model.addAttribute("notFound", false);
    }
    return "auth-login";
  }

  @PostMapping("/users/login")
  public String login(UserLoginServiceModel userModel,
      RedirectAttributes redirectAttributes) {

    if (userService.isLoginValid(userModel.getUsername(),
        userModel.getPassword())) {
      // user successfully logged in
      userService.loginUser(userModel.getUsername());
      return "redirect:/";
    } else {
        redirectAttributes.addFlashAttribute("userModel", userModel);
        redirectAttributes.addFlashAttribute("notFound", true);

        return "redirect:/users/login";
    }
  }

}

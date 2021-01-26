package bg.softuni.cars.web;

import bg.softuni.cars.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final CurrentUser currentUser;

  public HomeController(CurrentUser currentUser) {
    this.currentUser = currentUser;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("user", currentUser);
    return "index";
  }
}


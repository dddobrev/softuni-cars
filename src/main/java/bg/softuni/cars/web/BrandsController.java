package bg.softuni.cars.web;

import bg.softuni.cars.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandsController {

  private final BrandService brandService;

  public BrandsController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping("/all")
  public String getAllBrands(Model model) {
    model.addAttribute("brands", brandService.
        getAllBrands());
    return "brands";
  }
}

package bg.softuni.cars.web;

import bg.softuni.cars.models.view.OfferDetailsViewModel;
import bg.softuni.cars.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;

  public OffersController(OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/all")
  public String getAllOffers(Model model) {
    model.addAttribute("offers",
        offerService.getAllOffers());

    return "offers";
  }

  @GetMapping("/{id}")
  public String offerDetails(Model model,
      @PathVariable int id) {

    OfferDetailsViewModel offerDetailsModel = offerService.
        getOfferDetails(id).
        orElseThrow();

    model.addAttribute("offer", offerDetailsModel);

    return "details";
  }
}

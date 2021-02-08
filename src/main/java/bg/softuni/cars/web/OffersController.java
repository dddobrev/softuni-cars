package bg.softuni.cars.web;

import bg.softuni.cars.models.entities.enums.EngineEnum;
import bg.softuni.cars.models.entities.enums.TransmissionEnum;
import bg.softuni.cars.models.service.OfferServiceModel;
import bg.softuni.cars.models.view.OfferDetailsViewModel;
import bg.softuni.cars.services.BrandService;
import bg.softuni.cars.services.OfferService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;
  private final BrandService brandService;

  public OffersController(OfferService offerService,
      BrandService brandService) {
    this.offerService = offerService;
    this.brandService = brandService;
  }

  @GetMapping("/all")
  public String getAllOffers(Model model) {
    model.addAttribute("offers",
        offerService.getAllOffers());

    return "offers";
  }

  @ModelAttribute("offerModel")
  public OfferServiceModel offerModel(){
    return new OfferServiceModel();
  }

  @GetMapping("/add")
  public String newOffer(Model model) {
    model.addAttribute("engines", EngineEnum.values());
    model.addAttribute("transmissions", TransmissionEnum.values());
    model.addAttribute("brands", brandService.getAllBrands());
    return "offer-add";
  }

  @DeleteMapping("/delete/{id}")
  public String removeOffer(@PathVariable("id") long id) {

    offerService.removeOffer(id);

    return "redirect:/offers/all";
  }

  @PostMapping("/add")
  public String addOffer(@Valid @ModelAttribute OfferServiceModel offerModel,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("offerModel", offerModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

      return "redirect:/offers/add";
    }

    offerService.createOffer(offerModel);
    return "redirect:/offers/all";
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

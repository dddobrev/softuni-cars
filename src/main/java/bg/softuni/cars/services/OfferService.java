package bg.softuni.cars.services;

import bg.softuni.cars.models.view.OfferDetailsViewModel;
import bg.softuni.cars.models.view.OfferSummaryViewModel;
import java.util.List;
import java.util.Optional;

public interface OfferService {

  List<OfferSummaryViewModel> getAllOffers();

  Optional<OfferDetailsViewModel> getOfferDetails(long offerId);
}

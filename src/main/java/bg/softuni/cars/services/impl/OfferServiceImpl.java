package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.OfferEntity;
import bg.softuni.cars.models.view.OfferDetailsViewModel;
import bg.softuni.cars.models.view.OfferSummaryViewModel;
import bg.softuni.cars.repositories.OfferRepository;
import bg.softuni.cars.services.OfferService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public List<OfferSummaryViewModel> getAllOffers() {

    List<OfferEntity> offerEntities = offerRepository.findAll();

    return offerEntities.
        stream().
        map(OfferServiceImpl::map).
        collect(Collectors.toList());
  }

  @Override
  public Optional<OfferDetailsViewModel> getOfferDetails(int offerId) {

    Optional<OfferEntity> offerEntityOpt =
        offerRepository.findById(offerId);

    if (offerEntityOpt.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.empty();
    }
  }

  private static OfferSummaryViewModel map(OfferEntity offerEntity) {
    ModelMapper modelMapper = new ModelMapper();
    OfferSummaryViewModel offerModel = new OfferSummaryViewModel();
    modelMapper.map(offerEntity, offerModel);
    offerModel.
        setModel(offerEntity.getModel().getName()).
        setBrand(offerEntity.getModel().getBrand().getName());
    return offerModel;
  }
}

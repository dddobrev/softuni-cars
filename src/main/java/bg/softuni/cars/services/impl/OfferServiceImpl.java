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
        map(OfferServiceImpl::mapToSummary).
        collect(Collectors.toList());
  }

  @Override
  public Optional<OfferDetailsViewModel> getOfferDetails(long offerId) {
    return offerRepository.
        findById(offerId).
        map(OfferServiceImpl::mapToDetails);
  }

  private static OfferSummaryViewModel mapToSummary(OfferEntity offerEntity) {
    OfferSummaryViewModel offerModel = new OfferSummaryViewModel();
    mapToSummary(offerEntity, offerModel);
    return offerModel;
  }

  private static void mapToSummary(OfferEntity offerEntity, OfferSummaryViewModel offerModel) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.map(offerEntity, offerModel);
    offerModel.
        setModel(offerEntity.getModel().getName()).
        setBrand(offerEntity.getModel().getBrand().getName());
  }

  private static OfferDetailsViewModel mapToDetails(OfferEntity offerEntity) {
    OfferDetailsViewModel offerModel = new OfferDetailsViewModel();
    mapToSummary(offerEntity, offerModel);
    return offerModel;
  }
}

package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.ModelEntity;
import bg.softuni.cars.models.entities.OfferEntity;
import bg.softuni.cars.models.service.OfferServiceModel;
import bg.softuni.cars.models.view.OfferDetailsViewModel;
import bg.softuni.cars.models.view.OfferSummaryViewModel;
import bg.softuni.cars.repositories.ModelRepository;
import bg.softuni.cars.repositories.OfferRepository;
import bg.softuni.cars.repositories.UserRepository;
import bg.softuni.cars.security.CurrentUser;
import bg.softuni.cars.services.OfferService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ModelRepository modelRepository;
  private final CurrentUser currentUser;
  private final UserRepository userRepository;

  public OfferServiceImpl(OfferRepository offerRepository,
      ModelRepository modelRepository,
      CurrentUser currentUser,
      UserRepository userRepository) {
    this.offerRepository = offerRepository;
    this.modelRepository = modelRepository;
    this.currentUser = currentUser;
    this.userRepository = userRepository;
  }

  @Override
  public void createOffer(OfferServiceModel offerServiceModel) {

    OfferEntity newOffer = asNewOffer(offerServiceModel);

    ModelEntity model = modelRepository.findById(offerServiceModel.getModelId()).
        orElse(null);

    newOffer.setModel(model);
    offerRepository.save(newOffer);
  }

  private OfferEntity asNewOffer(OfferServiceModel offerServiceModel) {
    ModelMapper modelMapper = new ModelMapper();

    OfferEntity newOffer = new OfferEntity();

    modelMapper.map(offerServiceModel, newOffer);

    newOffer.setSeller(userRepository.findByUserName(currentUser.getUserName()).orElseThrow());
    newOffer.setId(null);

    return newOffer;
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
//    offerModel.
//        setSellerFirstName(offerEntity.getSeller().getFirstName()).
//        setSellerLastName(offerEntity.getSeller().getLastName());
    return offerModel;
  }
}

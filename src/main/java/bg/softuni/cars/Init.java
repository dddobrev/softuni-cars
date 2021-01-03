package bg.softuni.cars;

import bg.softuni.cars.models.entities.BrandEntity;
import bg.softuni.cars.models.entities.ModelEntity;
import bg.softuni.cars.models.entities.OfferEntity;
import bg.softuni.cars.models.entities.UserEntity;
import bg.softuni.cars.models.entities.UserRoleEntity;
import bg.softuni.cars.models.entities.enums.EngineEnum;
import bg.softuni.cars.models.entities.enums.TransmissionEnum;
import bg.softuni.cars.models.entities.enums.UserRoleEnum;
import bg.softuni.cars.models.entities.enums.VehicleCategoryEnum;
import bg.softuni.cars.repositories.BrandRepository;
import bg.softuni.cars.repositories.ModelRepository;
import bg.softuni.cars.repositories.OfferRepository;
import bg.softuni.cars.repositories.UserRepository;
import bg.softuni.cars.repositories.UserRoleRepository;
import java.time.Instant;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Initializes the database with some sample values.
 */
@Component
public class Init implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);

  private final BrandRepository brandRepository;
  private final ModelRepository modelRepository;
  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final OfferRepository offerRepository;

  public Init(BrandRepository brandRepository,
      ModelRepository modelRepository,
      UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      OfferRepository offerRepository) {

    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.brandRepository = brandRepository;
    this.modelRepository = modelRepository;
    this.offerRepository = offerRepository;
  }

  @Override
  public void run(String... args) {

    LOGGER.info("Database initializer starting...");
    if (brandRepository.count() == 0) {
      ModelEntity fiesta = initFiesta();
      initNC750();
      UserEntity user = initUserAndRoles();
      initOffer(user, fiesta);
    }
    LOGGER.info("Database initializer complete...");
  }

  private ModelEntity initFiesta() {
    BrandEntity brand = new BrandEntity();
    brand.
        setCreated(Instant.now()).
        setModified(Instant.now()).
        setName("Ford");
    brandRepository.save(brand);

    ModelEntity model = new ModelEntity();
    model.setBrand(brand).
        setCategory(VehicleCategoryEnum.CAR).
        setStartYear(1976).
        setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/2560px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg").
        setName("Fiesta").
        setCreated(Instant.now()).
        setModified(Instant.now());
    return modelRepository.save(model);
  }

  private void initNC750() {
    BrandEntity brand = new BrandEntity();
    brand.
        setCreated(Instant.now()).
        setModified(Instant.now()).
        setName("Honda");
    brandRepository.save(brand);

    ModelEntity model = new ModelEntity();
    model.setBrand(brand).
        setCategory(VehicleCategoryEnum.MOTORCYCLE).
        setStartYear(2012).
        setImageUrl("https://www.bultraco-sofia.bg/sites/default/files/overview_01_16.jpg").
        setName("NC").
        setCreated(Instant.now()).
        setModified(Instant.now());
    modelRepository.save(model);
  }

  private UserEntity initUserAndRoles() {

    UserRoleEntity userRole = new UserRoleEntity();
    userRole.
        setUserRole(UserRoleEnum.USER).
        setCreated(Instant.now()).
        setModified(Instant.now());

    UserRoleEntity adminRole = new UserRoleEntity();
    adminRole.
        setUserRole(UserRoleEnum.ADMIN).
        setCreated(Instant.now()).
        setModified(Instant.now());
    userRole = userRoleRepository.save(userRole);
    adminRole = userRoleRepository.save(adminRole);

    UserEntity user = new UserEntity();
    user.setFirstName("Lachezar").
        setLastName("Balev").
        setUserName("luchob").
        setActive(true).
        setImageUrl("https://avatars0.githubusercontent.com/u/10339738?s=460&u=5860fbe961c7216971cdb5102176834e3e836e64&v=4").
        setCreated(Instant.now()).
        setModified(Instant.now());

    user.setUserRoles(List.of(userRole, adminRole));
    return userRepository.save(user);
  }

  private void initOffer(UserEntity seller,
      ModelEntity model) {
    OfferEntity offer = new OfferEntity();
    offer.
        setDescription("Excellent fuel economy, convenient, cheap to run, nice sound system, fun to drive around town. Well maintained, good condition.").
        setEngine(EngineEnum.GASOLINE).
        setImageUrl("https://lh5.googleusercontent.com/vBRDsAqdC06eZf2llikj8kUnVBQ92nWrurLdjf29zrEugHkynC-YGOF2uaekeQrO0DMGqWyjmxlu3m-gJSQq=w2880-h1578-rw").
        setMileage(70000).
        setPrice(10000).
        setTransmission(TransmissionEnum.MANUAL).
        setYear(2016).
        setModel(model).
        setSeller(seller).
        setCreated(Instant.now()).
        setModified(Instant.now());
    offerRepository.save(offer);
  }
}

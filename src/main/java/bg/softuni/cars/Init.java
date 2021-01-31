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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder passwordEncoder;

  public Init(BrandRepository brandRepository,
      ModelRepository modelRepository,
      UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      OfferRepository offerRepository,
      PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.brandRepository = brandRepository;
    this.modelRepository = modelRepository;
    this.offerRepository = offerRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) {

    LOGGER.info("Database initializer starting...");
    if (brandRepository.count() == 0) {
      BrandEntity ford = initFord();
      ModelEntity fiesta = initFiesta(ford);
      initEscort(ford);
      initNC750();
      initUserAndRoles();
      initOffer(fiesta);
    }
    LOGGER.info("Database initializer complete...");
  }

  private BrandEntity initFord() {
    BrandEntity brand = new BrandEntity();
    brand.setName("Ford").
        setCreated(Instant.now()).
        setModified(Instant.now());
    return brandRepository.save(brand);
  }

  private ModelEntity initFiesta(BrandEntity ford) {
    ModelEntity model = new ModelEntity();
    model.setBrand(ford).
        setCategory(VehicleCategoryEnum.CAR).
        setStartYear(1976).
        setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/2560px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg").
        setName("Fiesta").
        setCreated(Instant.now()).
        setModified(Instant.now());
    return modelRepository.save(model);
  }

  private ModelEntity initEscort(BrandEntity ford) {
    ModelEntity model = new ModelEntity();
    model.setBrand(ford).
        setCategory(VehicleCategoryEnum.CAR).
        setStartYear(1968).
        setEndYear(2000).
        setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/1978_Ford_Escort_RS2000.jpg/1920px-1978_Ford_Escort_RS2000.jpg").
        setName("Escort").
        setCreated(Instant.now()).
        setModified(Instant.now());
    return modelRepository.save(model);
  }

  private void initNC750() {
    BrandEntity brand = new BrandEntity();
    brand.setName("Honda").
        setCreated(Instant.now()).
        setModified(Instant.now());
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

  private void initUserAndRoles() {

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

    UserEntity admin = new UserEntity();
    admin.setFirstName("Редник").
        setLastName("Текерлеков").
        setUserName("admin").
        setPassword(passwordEncoder.encode("topsecret")).
        setActive(true).
        setImageUrl("https://images06.snimka.bg/000633535.jpg?r=0").
        setCreated(Instant.now()).
        setModified(Instant.now());

    admin.setUserRoles(List.of(userRole, adminRole));


    UserEntity user = new UserEntity();
    user.setFirstName("Редник").
        setLastName("Манасиева").
        setUserName("user").
        setPassword(passwordEncoder.encode("topsecret")).
        setActive(true).
        setImageUrl("https://images06.snimka.bg/000633535.jpg?r=0").
        setCreated(Instant.now()).
        setModified(Instant.now());

    user.setUserRoles(List.of(userRole));

    userRepository.saveAll(List.of(admin, user));
  }

  private void initOffer(
      ModelEntity model) {

    UserEntity seller = userRepository.findAll().get(0);

    OfferEntity offer = new OfferEntity();
    offer.
        setDescription("Excellent fuel economy, convenient, cheap to run, nice sound system, fun to drive around town. Well maintained, good condition.").
        setEngine(EngineEnum.GASOLINE).
        setImageUrl("https://i.imgur.com/hMhtVqe.jpg").
        setMileage(70000).
        setPrice(10000).
        setTransmission(TransmissionEnum.MANUAL).
        setYear(2016).
        setModel(model).
        //setSeller(seller).
        setCreated(Instant.now()).
        setModified(Instant.now());
    offerRepository.save(offer);
  }
}

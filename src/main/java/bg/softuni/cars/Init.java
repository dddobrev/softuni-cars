package bg.softuni.cars;

import bg.softuni.cars.models.entities.BrandEntity;
import bg.softuni.cars.models.entities.ModelEntity;
import bg.softuni.cars.models.entities.VehicleCategoryEnum;
import bg.softuni.cars.repositories.BrandRepository;
import bg.softuni.cars.repositories.ModelRepository;
import java.time.Instant;
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

  public Init(BrandRepository brandRepository,
      ModelRepository modelRepository) {

    this.brandRepository = brandRepository;
    this.modelRepository = modelRepository;
  }

  @Override
  public void run(String... args) {

    LOGGER.info("Database initializer starting...");
    if (brandRepository.count() == 0) {
      initFiesta();
      initNC750();
    }
  }

  private void initFiesta() {
    BrandEntity brand = new BrandEntity();
    brand.
        setCreated(Instant.now()).
        setModified(Instant.now()).
        setName("Ford");
    brandRepository.save(brand);

    ModelEntity model = new ModelEntity();
    model.setBrand(brand).
        setCategory(VehicleCategoryEnum.CAR).
        setCreated(Instant.now()).
        setModified(Instant.now()).
        setStartYear(1976).
        setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/2560px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg").
        setName("Fiesta");
    modelRepository.save(model);
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
        setCreated(Instant.now()).
        setModified(Instant.now()).
        setStartYear(2012).
        setImageUrl("https://www.bultraco-sofia.bg/sites/default/files/overview_01_16.jpg").
        setName("NC");
    modelRepository.save(model);
  }
}

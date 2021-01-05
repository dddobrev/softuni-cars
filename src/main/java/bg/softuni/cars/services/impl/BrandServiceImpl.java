package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.BrandEntity;
import bg.softuni.cars.models.entities.ModelEntity;
import bg.softuni.cars.models.view.BrandViewModel;
import bg.softuni.cars.models.view.ModelViewModel;
import bg.softuni.cars.repositories.ModelRepository;
import bg.softuni.cars.services.BrandService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  private final ModelRepository modelRepository;

  public BrandServiceImpl(ModelRepository modelRepository) {
    this.modelRepository = modelRepository;
  }

  @Override
  public List<BrandViewModel> getAllBrands() {

    List<BrandViewModel> result = new ArrayList<>();
    List<ModelEntity> allModels =
        this.modelRepository.findAll();

    ModelMapper modelMapper = new ModelMapper();

    allModels.forEach(m -> {
      BrandEntity brand = m.getBrand();
      Optional<BrandViewModel> brandModel = findByName(brand.getName(), result);

      if (brandModel.isEmpty()) {
        BrandViewModel newModel = new BrandViewModel();
        modelMapper.map(brand, newModel);
        result.add(newModel);
        brandModel = Optional.of(newModel);
      }

      ModelViewModel newModel = new ModelViewModel();
      modelMapper.map(m, newModel);
      brandModel.get().addModel(newModel);
    });

    return result;
  }

  private static Optional<BrandViewModel> findByName(String name, List<BrandViewModel> brands) {
    return brands.
        stream().
        filter(b -> b.getName().equals(name)).
        findAny();
  }
}

package bg.softuni.cars.services.impl;

import bg.softuni.cars.models.entities.BrandEntity;
import bg.softuni.cars.models.view.BrandViewModel;
import bg.softuni.cars.models.view.ModelViewModel;
import bg.softuni.cars.repositories.BrandRepository;
import bg.softuni.cars.repositories.ModelRepository;
import bg.softuni.cars.services.BrandService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final ModelRepository moodelRepository;

  public BrandServiceImpl(BrandRepository brandRepository,
      ModelRepository moodelRepository) {
    this.brandRepository = brandRepository;
    this.moodelRepository = moodelRepository;
  }

  @Override
  public List<BrandViewModel> getAllBrands() {

    ModelMapper modelMapper = new ModelMapper();

    List<BrandEntity> brandEntities = this.brandRepository.findAll();
    return brandEntities.
        stream().
        map(be -> {
          BrandViewModel brandViewModel = new BrandViewModel();
          modelMapper.map(be, brandViewModel);
          return brandViewModel;
        }).collect(Collectors.toList());
  }

  // TODO: Extract all models for a given brand

//  private List<ModelViewModel> extractModels(BrandEntity brandEntity) {
//
//  }
}

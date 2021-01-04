package bg.softuni.cars.services;

import bg.softuni.cars.models.view.BrandViewModel;
import java.util.List;

public interface BrandService {

  List<BrandViewModel> getAllBrands();
}

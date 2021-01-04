package bg.softuni.cars.models.view;

import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {
  private String name;
  private List<ModelViewModel> models = new ArrayList<>();

  public String getName() {
    return name;
  }

  public BrandViewModel setName(String name) {
    this.name = name;
    return this;
  }

  public void addModel(ModelViewModel model) {
    this.models.add(model);
  }

  public List<ModelViewModel> getModels() {
    return models;
  }
}

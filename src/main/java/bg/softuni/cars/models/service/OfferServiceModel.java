package bg.softuni.cars.models.service;

import bg.softuni.cars.models.entities.enums.EngineEnum;
import bg.softuni.cars.models.entities.enums.TransmissionEnum;

public class OfferServiceModel {

  private EngineEnum engine;
  private TransmissionEnum transmission;
  private Integer year;

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferServiceModel setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferServiceModel setTransmission(
      TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public Integer getYear() {
    return year;
  }

  public OfferServiceModel setYear(Integer year) {
    this.year = year;
    return this;
  }

  @Override
  public String toString() {
    return "OfferServiceModel{" +
        "engine=" + engine +
        ", transmission=" + transmission +
        ", year=" + year +
        '}';
  }
}

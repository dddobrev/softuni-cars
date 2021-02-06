package bg.softuni.cars.models.service;

import bg.softuni.cars.models.entities.enums.EngineEnum;
import bg.softuni.cars.models.entities.enums.TransmissionEnum;
import bg.softuni.cars.models.validators.PresentOrPast;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OfferServiceModel {

  @NotNull
  private EngineEnum engine;
  @NotNull
  private TransmissionEnum transmission;
  @PresentOrPast(minYear = 1930)
  private Integer year;
  @NotNull
  private Long modelId;
  @NotNull
  @DecimalMin(value = "1")
  @DecimalMax(value = "1000000")
  private BigDecimal price;
  @NotNull
  @Positive
  private Integer mileage;
  @NotBlank
  @Size(min = 10)
  private String description;
  @NotBlank
  private String imageUrl;

  public Integer getMileage() {
    return mileage;
  }

  public OfferServiceModel setMileage(Integer mileage) {
    this.mileage = mileage;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferServiceModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferServiceModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferServiceModel setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Long getModelId() {
    return modelId;
  }

  public OfferServiceModel setModelId(Long modelId) {
    this.modelId = modelId;
    return this;
  }

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
        ", modelId=" + modelId +
        ", price=" + price +
        ", mileage=" + mileage +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }
}

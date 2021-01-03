package bg.softuni.cars.models.entities;

import bg.softuni.cars.models.entities.enums.VehicleCategoryEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="models")
public class ModelEntity extends BaseEntity {

  private String name;
  @Enumerated(EnumType.STRING)
  private VehicleCategoryEnum category;
  @Column(length = 512)
  private String imageUrl;
  @Column(nullable = false)
  private Integer startYear;
  private Integer endYear;
  @ManyToOne
  private BrandEntity brand;

  public String getName() {
    return name;
  }

  public ModelEntity setName(String name) {
    this.name = name;
    return this;
  }

  public VehicleCategoryEnum getCategory() {
    return category;
  }

  public ModelEntity setCategory(VehicleCategoryEnum category) {
    this.category = category;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ModelEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public Integer getStartYear() {
    return startYear;
  }

  public ModelEntity setStartYear(Integer startYear) {
    this.startYear = startYear;
    return this;
  }

  public Integer getEndYear() {
    return endYear;
  }

  public ModelEntity setEndYear(Integer endYear) {
    this.endYear = endYear;
    return this;
  }

  public BrandEntity getBrand() {
    return brand;
  }

  public ModelEntity setBrand(BrandEntity brand) {
    this.brand = brand;
    return this;
  }

  @Override
  public String toString() {
    return "ModelEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category=" + category +
        ", imageUrl='" + imageUrl + '\'' +
        ", startYear=" + startYear +
        ", endYear=" + endYear +
        ", created=" + created +
        ", modified=" + modified +
        ", brand=" + brand +
        '}';
  }
}

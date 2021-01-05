package bg.softuni.cars.models.view;

public class OfferSummaryViewModel {
  private String model;
  private String brand;
  private String engine;
  private String imageUrl;
  private int mileage;
  private int price;
  private String transmission;
  private int year;

  public String getModel() {
    return model;
  }

  public OfferSummaryViewModel setModel(String model) {
    this.model = model;
    return this;
  }

  public String getBrand() {
    return brand;
  }

  public OfferSummaryViewModel setBrand(String brand) {
    this.brand = brand;
    return this;
  }

  public String getEngine() {
    return engine;
  }

  public OfferSummaryViewModel setEngine(String engine) {
    this.engine = engine;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferSummaryViewModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferSummaryViewModel setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public int getPrice() {
    return price;
  }

  public OfferSummaryViewModel setPrice(int price) {
    this.price = price;
    return this;
  }

  public String getTransmission() {
    return transmission;
  }

  public OfferSummaryViewModel setTransmission(String transmission) {
    this.transmission = transmission;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferSummaryViewModel setYear(int year) {
    this.year = year;
    return this;
  }
}

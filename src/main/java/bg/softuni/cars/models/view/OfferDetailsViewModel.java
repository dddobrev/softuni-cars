package bg.softuni.cars.models.view;

import java.time.Instant;

public class OfferDetailsViewModel extends OfferSummaryViewModel {

  private Instant offerCreated;
  private Instant offerModified;
  private String sellerFirstName;
  private String sellerLastName;

  public Instant getOfferCreated() {
    return offerCreated;
  }

  public OfferDetailsViewModel setOfferCreated(Instant offerCreated) {
    this.offerCreated = offerCreated;
    return this;
  }

  public Instant getOfferModified() {
    return offerModified;
  }

  public OfferDetailsViewModel setOfferModified(Instant offerModified) {
    this.offerModified = offerModified;
    return this;
  }

  public String getSellerFirstName() {
    return sellerFirstName;
  }

  public OfferDetailsViewModel setSellerFirstName(String sellerFirstName) {
    this.sellerFirstName = sellerFirstName;
    return this;
  }

  public String getSellerLastName() {
    return sellerLastName;
  }

  public OfferDetailsViewModel setSellerLastName(String sellerLastName) {
    this.sellerLastName = sellerLastName;
    return this;
  }
}

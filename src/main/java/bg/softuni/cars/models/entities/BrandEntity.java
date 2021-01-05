package bg.softuni.cars.models.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="brands")
public class BrandEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Instant getCreated() {
    return created;
  }

  public BrandEntity setCreated(Instant created) {
    this.created = created;
    return this;
  }

  public Instant getModified() {
    return modified;
  }

  public BrandEntity setModified(Instant modified) {
    this.modified = modified;
    return this;
  }

  @Override
  public String toString() {
    return "BrandEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        '}';
  }
}

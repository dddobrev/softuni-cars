package bg.softuni.cars.models.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;
  @Column(nullable = false)
  protected Instant created;
  @Column(nullable = false)
  protected Instant modified;

  public Long getId() {
    return id;
  }

  public BaseEntity setId(Long id) {
    this.id = id;
    return this;
  }

  @PrePersist
  public void prePersist() {
    setCreated(Instant.now());
    setModified(Instant.now());
  }

  @PreUpdate
  public void preUpdate() {
    setModified(Instant.now());
  }

  public Instant getCreated() {
    return created;
  }

  public BaseEntity setCreated(Instant created) {
    this.created = created;
    return this;
  }

  public Instant getModified() {
    return modified;
  }

  public BaseEntity setModified(Instant modified) {
    this.modified = modified;
    return this;
  }
}

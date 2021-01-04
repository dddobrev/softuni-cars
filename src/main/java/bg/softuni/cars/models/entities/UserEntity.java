package bg.softuni.cars.models.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {

  private String userName;
  private String firstName;
  private String lastName;
  private boolean isActive;

  //if OneToMany there will be a unique key in the rel table
  @ManyToMany
  private List<UserRoleEntity> userRoles;
  private String imageUrl;

  public List<UserRoleEntity> getUserRoles() {
    return userRoles;
  }

  public UserEntity setUserRoles(
      List<UserRoleEntity> userRoles) {
    this.userRoles = userRoles;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public UserEntity setActive(boolean active) {
    isActive = active;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public UserEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "userName='" + userName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", isActive=" + isActive +
        ", userRoles=" + userRoles +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }
}
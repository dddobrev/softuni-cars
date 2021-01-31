package bg.softuni.cars.security;

import bg.softuni.cars.models.entities.enums.UserRoleEnum;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {

  private String userName;
  private boolean anonymous;
  private Set<UserRoleEnum> userRoles = EnumSet.noneOf(UserRoleEnum.class);

  public CurrentUser() {
    this.userName = "anonymous";
    this.anonymous = true;
  }

  public String getUserName() {
    return userName;
  }

  public CurrentUser setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public boolean isAnonymous() {
    return anonymous;
  }

  public boolean isLoggedIn() {
    return !isAnonymous();
  }

  public CurrentUser setAnonymous(boolean anonymous) {
    this.anonymous = anonymous;
    if (anonymous) {
      this.userName = "anonymous";
      this.userRoles.clear();
    }
    return this;
  }

  public Set<UserRoleEnum> getUserRoles() {
    return userRoles;
  }

  public CurrentUser setUserRoles(
      Set<UserRoleEnum> userRoles) {
    this.userRoles = userRoles;
    return this;
  }

  public CurrentUser addUserRoles(List<UserRoleEnum> userRoles) {
    this.userRoles.addAll(userRoles);
    return this;
  }

  public boolean isAdmin() {
    return userRoles.contains(UserRoleEnum.ADMIN);
  }
}

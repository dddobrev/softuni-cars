package bg.softuni.cars.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {

  private String userName;
  private boolean anonymous;

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
    }
    return this;
  }
}

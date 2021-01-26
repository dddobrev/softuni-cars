package bg.softuni.cars.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

  private String userName;
  private boolean anonymous;

  public CurrentUser(){
    this.anonymous = true;
    this.userName = "anonymous";
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

  public CurrentUser setAnonymous(boolean anonymous) {
    this.anonymous = anonymous;
    return this;
  }
}

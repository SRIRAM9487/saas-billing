package com.saas.billing_system.user.application.event;

import org.springframework.context.ApplicationEvent;

public class EmailVerificationEvent extends ApplicationEvent {

  private final String email;
  private final String verificationToken;
  private final String userId;

  public EmailVerificationEvent(Object source, String email, String userId, String verificationToken) {
    super(source);
    this.email = email;
    this.verificationToken = verificationToken;
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public String getVerificationToken() {
    return verificationToken;
  }

  public String getUserId() {
    return userId;
  }


}

package com.saas.billing_system.user.application.event;

import org.springframework.context.ApplicationEvent;

public class LoginVerificationEvent extends ApplicationEvent {

  private final String email;
  private final String otp;

  public LoginVerificationEvent(Object source, String email, String otp) {
    super(source);
    this.email = email;
    this.otp = otp;
  }

  public String getEmail() {
    return email;
  }

  public String getOtp() {
    return otp;
  }

}

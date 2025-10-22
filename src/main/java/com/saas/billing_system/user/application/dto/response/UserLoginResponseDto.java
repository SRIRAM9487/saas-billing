package com.saas.billing_system.user.application.dto.response;

public record UserLoginResponseDto(String token) {

  public static UserLoginResponseDto verificationSent() {
    return new UserLoginResponseDto("Login otp has sent to your email");
  }

  public static UserLoginResponseDto verificationSuccess(String token){
    return new UserLoginResponseDto(token);
  }

}

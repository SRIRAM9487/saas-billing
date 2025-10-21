package com.saas.billing_system.user.application.dto.request;

public record UserRegisterRequestDto(
    String userName,
    String email,
    double phone,
    String password) {

}

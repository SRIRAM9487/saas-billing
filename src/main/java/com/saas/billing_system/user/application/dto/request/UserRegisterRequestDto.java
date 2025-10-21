package com.saas.billing_system.user.application.dto.request;

import com.saas.billing_system.user.domain.entity.User;

public record UserRegisterRequestDto(
    String userName,
    String email,
    double phone,
    String password) {

}

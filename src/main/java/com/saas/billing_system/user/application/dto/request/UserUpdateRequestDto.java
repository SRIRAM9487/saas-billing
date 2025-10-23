package com.saas.billing_system.user.application.dto.request;

public record UserUpdateRequestDto(
    String userName,
    String password,
    String email,
    String phoneNumber) {
}

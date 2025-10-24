package com.saas.billing_system.user.infrastructure.dto.request;

public record UserUpdateRequestDto(
    String userName,
    String password,
    String email,
    String phoneNumber) {
}

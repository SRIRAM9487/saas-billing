package com.saas.billing_system.tenant.infrastructure.dto.request;

import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.user.infrastructure.dto.request.UserRegisterRequestDto;

public record TenantCreateRequestDto(
    String name,
    int currency,
    String addressLine1,
    String addressLine2,
    String city,
    String district,
    String state,
    String postalCode,
    String country,
    UserRegisterRequestDto userRequestDto) {

  public static Tenant toTenant(TenantCreateRequestDto requestDto, String apikey, UUID userId) {

    return Tenant.create(
        requestDto.name(),
        requestDto.addressLine1(),
        requestDto.addressLine2(),
        requestDto.city(),
        requestDto.district(),
        requestDto.state(),
        requestDto.postalCode(),
        requestDto.country(),
        requestDto.currency(),
        apikey,
        userId);
  }

  public static Tenant toTenant(TenantCreateRequestDto requestDto, UUID userId) {

    return Tenant.create(
        requestDto.name(),
        requestDto.addressLine1(),
        requestDto.addressLine2(),
        requestDto.city(),
        requestDto.district(),
        requestDto.state(),
        requestDto.postalCode(),
        requestDto.country(),
        requestDto.currency(),
        userId);
  }
}

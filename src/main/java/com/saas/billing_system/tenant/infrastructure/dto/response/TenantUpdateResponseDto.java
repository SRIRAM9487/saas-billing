package com.saas.billing_system.tenant.infrastructure.dto.response;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.infrastructure.dto.response.UserUpdateResponseDto;

public record TenantUpdateResponseDto(
    String name,
    int currency,
    String addressLine1,
    String addressLine2,
    String city,
    String district,
    String state,
    String postalCode,
    String country,
    UserUpdateResponseDto responseDto) {

  public static TenantUpdateResponseDto fromTenant(Tenant tenant, User user) {
    return new TenantUpdateResponseDto(
        tenant.getName(),
        tenant.getDefaultCurrency().getId(),
        tenant.getAddress().addressLine1(),
        tenant.getAddress().addressLine2(),
        tenant.getAddress().city(),
        tenant.getAddress().district(),
        tenant.getAddress().state(),
        tenant.getAddress().postalCode(),
        tenant.getAddress().country(),
        UserUpdateResponseDto.fromUser(user));
  }

}

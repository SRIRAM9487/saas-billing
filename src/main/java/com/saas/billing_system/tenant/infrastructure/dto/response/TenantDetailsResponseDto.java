package com.saas.billing_system.tenant.infrastructure.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.infrastructure.dto.response.UserDetailsResponseDto;

public record TenantDetailsResponseDto(
    UUID tenantId,
    String name,
    String addressLine1,
    String addressLine2,
    String city,
    String district,
    String state,
    String postalCode,
    String country,
    String defaultCurrency,
    UUID userId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    UserDetailsResponseDto user) {

  public static TenantDetailsResponseDto fromTenant(Tenant tenant, User user) {
    var address = tenant.getAddress();
    return new TenantDetailsResponseDto(
        tenant.getId().id(),
        tenant.getName(),
        address.addressLine1(),
        address.addressLine2(),
        address.city(),
        address.district(),
        address.state(),
        address.postalCode(),
        address.country(),
        tenant.getDefaultCurrency().getCode(),
        tenant.getUser(),
        tenant.getCreatedAt(),
        tenant.getUpdatedAt(),
        tenant.getDeletedAt(),
        UserDetailsResponseDto.fromUser(user));
  }
}

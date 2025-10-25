package com.saas.billing_system.tenant.domain.vo;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public record TenantId(UUID id) {

  public static TenantId create() {
    return new TenantId(UUID.randomUUID());
  }
  public static TenantId create(String id) {
    return new TenantId(UUID.fromString(id));
  }
}

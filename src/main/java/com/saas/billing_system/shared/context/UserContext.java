package com.saas.billing_system.shared.context;

import java.util.UUID;

public class UserContext {
  private UUID userId;
  private UUID tenantId;

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public UUID getTenantId() {
    return tenantId;
  }

  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

}

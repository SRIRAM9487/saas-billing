package com.saas.billing_system.tenant.infrastructure.dto.response;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.user.domain.entity.User;

public record TenantDeleteResponsetDto(String tenantId, String userId) {

  public static TenantDeleteResponsetDto create(String tenantId, String userId) {
    return new TenantDeleteResponsetDto(tenantId, userId);
  }

  public static TenantDeleteResponsetDto create(Tenant tenat, User user) {
    return new TenantDeleteResponsetDto(tenat.getId().id().toString(), user.getId().id().toString());
  }

}

package com.saas.billing_system.tenant.application.usecase;

import com.saas.billing_system.tenant.application.service.TenantService;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantDeleteResponsetDto;
import com.saas.billing_system.user.application.usecase.UserDeleteUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantDeleteUseCase {

  private static final Logger log = LoggerFactory.getLogger(TenantDeleteUseCase.class);

  private final UserDeleteUseCase userDeleteUseCase;
  private final TenantService tenantService;

  public TenantDeleteResponsetDto delete(String userId) {
    log.info("Deleting tenant and user for userId: {}", userId);

    Tenant tenant = tenantService.deleteTenant(userId);
    log.debug("Tenant soft-deleted for userId: {}", userId);

    User user = userDeleteUseCase.deleteUser(userId);
    log.debug("User soft-deleted for userId: {}", userId);

    TenantDeleteResponsetDto response = TenantDeleteResponsetDto.create(tenant, user);
    log.info("Tenant and user deletion completed for userId: {}", userId);
    return response;
  }
}

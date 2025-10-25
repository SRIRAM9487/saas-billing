package com.saas.billing_system.tenant.application.usecase;

import com.saas.billing_system.tenant.application.service.TenantService;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantUpdateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantUpdateResponseDto;
import com.saas.billing_system.user.application.usecase.UserUpdateUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantUpdateUseCase {

  private static final Logger log = LoggerFactory.getLogger(TenantUpdateUseCase.class);

  private final TenantService tenantService;
  private final UserUpdateUseCase userUpdateUseCase;

  public TenantUpdateResponseDto update(String userId, TenantUpdateRequestDto requestDto) {
    log.info("Updating tenant and user for userId: {}", userId);

    Tenant tenant = tenantService.updateTenant(userId, requestDto);
    log.debug("Tenant updated for userId: {}", userId);

    User user = userUpdateUseCase.updateUser(userId, requestDto.userRequestDto());
    log.debug("User updated for userId: {}", userId);

    TenantUpdateResponseDto response = TenantUpdateResponseDto.fromTenant(tenant, user);
    log.info("Tenant and user update completed for userId: {}", userId);
    return response;
  }
}

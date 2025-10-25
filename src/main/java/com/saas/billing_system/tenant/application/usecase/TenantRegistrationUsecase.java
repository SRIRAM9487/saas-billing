package com.saas.billing_system.tenant.application.usecase;

import com.saas.billing_system.tenant.application.service.TenantService;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantCreateResponseDto;
import com.saas.billing_system.user.application.usecase.UserRegistrationUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantRegistrationUsecase {

  private static final Logger log = LoggerFactory.getLogger(TenantRegistrationUsecase.class);

  private final UserRegistrationUseCase userRegistrationUseCase;
  private final TenantService tenantService;

  public TenantCreateResponseDto register(TenantCreateRequestDto requestDto) {
    log.info("Starting tenant registration for email: {}", requestDto.userRequestDto().email());

    User user = userRegistrationUseCase.register(requestDto.userRequestDto());
    log.debug("User registered with ID: {}", user.getId().id());

    Tenant tenant = tenantService.create(requestDto, user.getId().id());
    log.info("Tenant created with ID: {}", tenant.getId());

    TenantCreateResponseDto response = TenantCreateResponseDto.fromTenant(tenant, user);
    log.debug("Tenant registration completed for user: {}", user.getEmail());
    return response;
  }
}

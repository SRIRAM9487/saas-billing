package com.saas.billing_system.tenant.application.usecase;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantCreateResponseDto;
import com.saas.billing_system.user.application.usecase.UserRegistrationUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantRegistrationUsecase {

  private final TenantCreateUseCase tenantCreateUseCase;
  private final UserRegistrationUseCase userRegistrationUseCase;

  public TenantCreateResponseDto register(TenantCreateRequestDto requestDto) {

    User user = userRegistrationUseCase.register(requestDto.userRequestDto());

    Tenant tenant = tenantCreateUseCase.create(requestDto, user.getId().id());

    return TenantCreateResponseDto.fromTenant(tenant, user);
  }

}

package com.saas.billing_system.tenant.application.usecase;

import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.persistence.TenantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantCreateUseCase {

  private final Logger log = LoggerFactory.getLogger(TenantCreateUseCase.class);
  private final TenantRepository tenantRepo;
  private final ApiGeneratorUseCase apiGeneratorUseCase;

  public Tenant create(TenantCreateRequestDto requestDto, UUID userId) {
    log.trace("creating new tenant.");

    Tenant tenant = tenantRepo.save(TenantCreateRequestDto.toTenant(requestDto,
        apiGeneratorUseCase.generateApi(userId.toString()), userId));

    log.trace("Tenant created {}", tenant.toString());

    return tenant;
  }

}

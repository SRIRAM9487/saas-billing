package com.saas.billing_system.tenant.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.domain.vo.TenantId;
import com.saas.billing_system.tenant.infrastructure.persistence.TenantRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantFactory {

  private final TenantRepository tenantRepository;

  public Optional<Tenant> findTenantById(String id) {

    return tenantRepository.findById(TenantId.create(id));
  }

  public Optional<Tenant> findTenantByUserId(UUID id) {

    return tenantRepository.findTenantByUser(id);
  }

  public List<Tenant> findAll() {
    return tenantRepository.findAll();
  }
}

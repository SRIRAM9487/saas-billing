package com.saas.billing_system.tenant.application.service;

import java.util.Optional;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.persistence.TenantRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantFactory {

  private final TenantRepository tenantRepository;

  public Optional<Tenant> findTenantById(String id) {

    return null;
  }
}

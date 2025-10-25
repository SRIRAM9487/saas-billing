package com.saas.billing_system.tenant.application.service;

import java.util.List;
import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.domain.exception.TenantException;
import com.saas.billing_system.tenant.domain.vo.Address;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantUpdateRequestDto;
import com.saas.billing_system.tenant.infrastructure.persistence.TenantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

  private final Logger log = LoggerFactory.getLogger(TenantService.class);
  private final TenantRepository tenantRepo;
  private final TenantFactory tenantFactory;

  public List<Tenant> getAll() {
    List<Tenant> tenants = tenantFactory.findAll();
    if (tenants == null)
      throw TenantException.notFound();
    return tenants;
  }

  public Tenant getTenantByUserId(String id) {

    Tenant tenant = tenantFactory.findTenantByUserId(UUID.fromString(id)).orElseThrow(() -> {
      log.trace("Tenant not found : ", id);
      return TenantException.notFound(id);
    });

    return tenant;
  }

  public Tenant create(TenantCreateRequestDto requestDto, UUID userId) {
    log.trace("creating new tenant.");
    Tenant tenant = tenantRepo.save(TenantCreateRequestDto.toTenant(requestDto, userId));
    log.trace("Tenant created {}", tenant.toString());
    return tenant;
  }

  public Tenant deleteTenant(String id) {

    Tenant tenant = tenantFactory.findTenantByUserId(UUID.fromString(id)).orElseThrow(() -> {
      return TenantException.notFound(id);
    });

    tenant.softDelete();

    Tenant deletedTenant = tenantRepo.save(tenant);

    return deletedTenant;
  }

  public Tenant getTenantById(String id) {

    Tenant tenant = tenantFactory.findTenantById(id).orElseThrow(() -> {
      log.trace("Tenant not found : ", id);
      return TenantException.notFound(id);
    });

    return tenant;
  }

  public Tenant updateTenant(String userId, TenantUpdateRequestDto requestDto) {
    Tenant tenant = tenantFactory.findTenantByUserId(UUID.fromString(userId)).orElseThrow(() -> {
      log.trace("Tenant not found");
      return TenantException.notFound(userId);
    });

    tenant.setName(requestDto.name());
    tenant.setAddress(new Address(requestDto.addressLine1(), requestDto.addressLine2(), requestDto.city(),
        requestDto.district(), requestDto.state(), requestDto.postalCode(), requestDto.country()));

    tenant.setDefaultCurrency(DefaultCurrency.fromId(requestDto.currency()));

    Tenant updatedTenant = tenantRepo.save(tenant);

    log.trace("Tenant updated successfully");
    return updatedTenant;
  }
}

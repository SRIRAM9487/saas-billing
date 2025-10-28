package com.saas.billing_system.tenant.application.usecase;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.saas.billing_system.tenant.application.service.TenantService;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantDetailsResponseDto;
import com.saas.billing_system.user.application.service.UserDetailsUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantDetailsUseCase {

  private static final Logger log = LoggerFactory.getLogger(TenantDetailsUseCase.class);

  private final TenantService tenantService;
  private final UserDetailsUseCase userDetailsUseCase;

  public List<TenantDetailsResponseDto> getAll() {
    log.debug("Fetching all tenants");
    List<Tenant> tenants = tenantService.getAll();
    List<User> users = userDetailsUseCase.getAll();

    Map<UUID, User> userMap = users.stream()
        .collect(Collectors.toMap(u -> u.getId().id(), u -> u));

    List<TenantDetailsResponseDto> dtos = tenants.stream().map(t -> {
      User user = userMap.get(t.getUser());
      if (user != null) {
        return TenantDetailsResponseDto.fromTenant(t, user);
      }
      return null;
    }).toList();

    log.debug("Found {} tenants", tenants.size());

    return dtos;
  }

  public TenantDetailsResponseDto getById(UUID id) {
    log.debug("Fetching tenant by ID: {}", id);
    Tenant tenant = tenantService.getTenantById(id.toString());
    User user = userDetailsUseCase.getUserById(tenant.getUser().toString());

    TenantDetailsResponseDto dto = TenantDetailsResponseDto.fromTenant(tenant, user);
    log.debug("Tenant found for ID: {}", id);
    return dto;
  }

  public TenantDetailsResponseDto getByUserId(String id) {
    log.debug("Fetching tenant by user ID: {}", id);
    Tenant tenant = tenantService.getTenantByUserId(id);
    User user = userDetailsUseCase.getUserById(tenant.getUser().toString());

    TenantDetailsResponseDto dto = TenantDetailsResponseDto.fromTenant(tenant, user);
    log.debug("Tenant found for user ID: {}", id);
    return dto;
  }
}

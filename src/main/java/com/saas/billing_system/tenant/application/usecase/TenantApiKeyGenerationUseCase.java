package com.saas.billing_system.tenant.application.usecase;

import java.util.Map;

import com.saas.billing_system.tenant.application.service.ApiKeyService;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.domain.exception.TenantException;
import com.saas.billing_system.tenant.infrastructure.persistence.TenantRepository;
import com.saas.billing_system.user.application.service.UserFactory;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.UserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantApiKeyGenerationUseCase {

  private final Logger log = LoggerFactory.getLogger(TenantApiKeyGenerationUseCase.class);
  private final ApiKeyService apiKeyService;
  private final TenantRepository tenantRepo;
  private final UserFactory userFactory;

  public String generateApiKey(String userId) {

    log.trace("Generating Api Key");

    User user = userFactory.findUserById(userId).orElseThrow(() -> {
      log.trace("User not found : {}", userId);
      return UserException.notFound();
    });

    Tenant tenant = tenantRepo.findTenantByUser(user.getId().id()).orElseThrow(() -> {
      log.trace("Tenant with id not found : {}", userId);
      return TenantException.notFound(userId);
    });

    Map<String, String> key = apiKeyService.generateApi(userId);

    String apiKey = key.get("key");
    String hash = key.get("hash");

    tenant.updateApikey(hash);

    tenantRepo.save(tenant);

    log.trace("Api Key generated and tenant details updated");

    return apiKey;
  }

}

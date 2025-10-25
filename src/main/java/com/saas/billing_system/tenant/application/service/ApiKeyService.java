package com.saas.billing_system.tenant.application.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiKeyService {
  private final ApiKeySecurityService apiKeySecurityService;
  private final Logger log = LoggerFactory.getLogger(ApiKeyService.class);

  public Map<String, String> generateApi(String userId) {
    log.trace("Generating Api Key for user {}", userId);
    String plainKey = apiKeySecurityService.generatePlainApiKey();
    String rawKey = "sk_" + userId + plainKey;
    String hashed = apiKeySecurityService.hashApiKey(rawKey);
    log.trace("Api Key generated succussfully", userId);
    return Map.of(rawKey, hashed);
  }

}

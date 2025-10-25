package com.saas.billing_system.tenant.application.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiKeySecurityService {

  private static final SecureRandom RNG = new SecureRandom();
  private static final int SALT_LENGTH = 16;
  private static final int HASH_LENGTH = 32;
  private static final int PARALLELISM = 1;
  private static final int MEMORY = 65536;
  private static final int ITERATIONS = 3;

  private static final Argon2PasswordEncoder ENCODER = new Argon2PasswordEncoder(SALT_LENGTH, HASH_LENGTH, PARALLELISM,
      MEMORY, ITERATIONS);

  public String generatePlainApiKey() {
    byte[] randomBytes = new byte[32];
    RNG.nextBytes(randomBytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
  }

  public String hashApiKey(String apiKey) {
    return ENCODER.encode(apiKey);
  }

  public boolean verifyApiKey(String rawKey, String hashedKey) {
    return ENCODER.matches(rawKey, hashedKey);
  }

}

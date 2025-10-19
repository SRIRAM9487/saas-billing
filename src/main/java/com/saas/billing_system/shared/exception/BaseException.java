package com.saas.billing_system.shared.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import com.saas.billing_system.shared.context.UserContextHolder;

public abstract class BaseException extends RuntimeException {

  private final String message;
  private final String logMessage;
  private final UUID userId;
  private final UUID tenantId;
  private final LocalDateTime exceptionTime;

  public BaseException(String message,String logMessage) {
    super(message);
    this.message = message;
    this.userId = UserContextHolder.getUserId();
    this.tenantId = UserContextHolder.getTenantId();
    this.exceptionTime = LocalDateTime.now();
    this.logMessage = logMessage;
  }

}

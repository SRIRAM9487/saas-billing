package com.saas.billing_system.tenant.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.tenant.domain.exception.types.TenantExceptionType;

public class TenantException extends BaseException {

  public TenantException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static TenantException notFound() {
    String message = "Tenant not found";
    return new TenantException(message, message, TenantExceptionType.TENANT_NOT_FOUND.name());
  }

  public static TenantException notFound(String id) {
    String message = "Tenant with user id " + id + " not found";
    return new TenantException(message, message, TenantExceptionType.TENANT_NOT_FOUND.name());
  }

}

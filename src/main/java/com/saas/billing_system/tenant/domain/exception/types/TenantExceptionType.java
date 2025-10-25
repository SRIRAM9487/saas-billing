package com.saas.billing_system.tenant.domain.exception.types;

public enum TenantExceptionType {

  TENANT_NOT_FOUND("Tenant not found");

  private final String message;

  TenantExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}

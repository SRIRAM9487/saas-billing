package com.saas.billing_system.plan.domain.exception.type;

public enum FeatureExceptionTypes {
  NOT_FOUND("Feature not found");

  private final String message;

  FeatureExceptionTypes(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}

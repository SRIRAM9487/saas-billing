package com.saas.billing_system.plan.domain.exception.type;

public enum PlanExceptionTypes {

  NOT_FOUND("Plan not found");

  private final String message;

  PlanExceptionTypes(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}

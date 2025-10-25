package com.saas.billing_system.plan.domain.exception;

import com.saas.billing_system.plan.domain.exception.type.PlanExceptionTypes;
import com.saas.billing_system.shared.exception.BaseException;

public class PlanException extends BaseException {

  public PlanException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static PlanException notFound() {
    String message = PlanExceptionTypes.NOT_FOUND.getMessage();
    return new PlanException(message, message, PlanExceptionTypes.NOT_FOUND.name());
  }

  public static PlanException notFound(String id) {
    String message = PlanExceptionTypes.NOT_FOUND.getMessage() + " " + id;
    return new PlanException(message, message, PlanExceptionTypes.NOT_FOUND.name());
  }

}

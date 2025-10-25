package com.saas.billing_system.plan.domain.exception;

import com.saas.billing_system.plan.domain.exception.type.FeatureExceptionTypes;
import com.saas.billing_system.shared.exception.BaseException;

public class FeatureException extends BaseException {

  public FeatureException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static FeatureException notFound() {
    String message = FeatureExceptionTypes.NOT_FOUND.getMessage();
    return new FeatureException(message, message, FeatureExceptionTypes.NOT_FOUND.name());
  }

  public static FeatureException notFound(String id) {
    String message = FeatureExceptionTypes.NOT_FOUND.getMessage() + " " + id;
    return new FeatureException(message, message, FeatureExceptionTypes.NOT_FOUND.name());
  }
}

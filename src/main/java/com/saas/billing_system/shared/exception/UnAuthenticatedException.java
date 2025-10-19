package com.saas.billing_system.shared.exception;

import com.saas.billing_system.shared.context.UserContextHolder;

public class UnAuthenticatedException extends BaseException {

  public UnAuthenticatedException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static UnAuthenticatedException create() {
    return new UnAuthenticatedException(
        "Authentication failed",
        "Authentication failed for " + UserContextHolder.getUserId(),
        "Invalid User Name or Password");
  }

  public static UnAuthenticatedException userIdMissing() {
    return new UnAuthenticatedException(
        "Authentication failed",
        "User id missing",
        "User id missing");
  }

}

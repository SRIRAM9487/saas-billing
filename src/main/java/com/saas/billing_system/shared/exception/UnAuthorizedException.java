package com.saas.billing_system.shared.exception;

import com.saas.billing_system.shared.context.UserContextHolder;

public class UnAuthorizedException extends BaseException {

  public UnAuthorizedException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static UnAuthorizedException create() {
    return new UnAuthorizedException(
        "Authorization failed",
        "Authroization failed for " + UserContextHolder.getUserId(),
        "Invalid User Name or Password");
  }

  public static UnAuthorizedException userIdMissing() {
    return new UnAuthorizedException(
        "Authorization failed",
        "User id missing",
        "User id missing");
  }
}

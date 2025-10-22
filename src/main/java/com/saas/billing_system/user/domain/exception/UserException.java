package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.UserExceptionType;

public class UserException extends BaseException {

  public UserException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static UserException notFound(String id) {
    return new UserException("User with id " + id+" not found", "User with the id "+id+" not found", UserExceptionType.USER_NOT_FOUND.name());
  }

  public static UserException authenticationFailed(String id){
    return new UserException("User with id " + id+" not found", "User with the id "+id+" not found", UserExceptionType.USER_NOT_FOUND.name());
  }

  public static UserException emailNotVerified(String id){
    return new UserException("User with id " + id+" not found", "User with the id "+id+" not found", UserExceptionType.USER_NOT_FOUND.name());
  }

  public static UserException invalidOtp(String id){
    return new UserException("User with id " + id+" not found", "User with the id "+id+" not found", UserExceptionType.USER_NOT_FOUND.name());
  }
}

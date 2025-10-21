package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;

public class PhoneException extends BaseException{

  public PhoneException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  
}

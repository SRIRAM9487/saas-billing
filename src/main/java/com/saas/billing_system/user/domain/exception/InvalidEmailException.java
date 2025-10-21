package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;

public class InvalidEmailException extends BaseException{

  public InvalidEmailException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static InvalidEmailException missingAtSymbol(){
    return new InvalidEmailException("Missing @ symbol", "Invalid email Missing @ Symbol","" );
  }


  public static InvalidEmailException empty(){
    return new InvalidEmailException(InvalidEmailTypes.EMPTY_EMAIL.getMessage(), "", InvalidEmailTypes.EMPTY_EMAIL.name());
  }
  
}

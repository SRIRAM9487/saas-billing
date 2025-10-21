package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.EmailExceptionType;

public class EmailException extends BaseException{

  public EmailException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static EmailException missingAtSymbol(){
    return new EmailException("Missing @ symbol", "Invalid email Missing @ Symbol","" );
  }


  public static EmailException empty(){
    return new EmailException(EmailExceptionType.EMPTY_EMAIL.getMessage(), "", EmailExceptionType.EMPTY_EMAIL.name());
  }
  
}

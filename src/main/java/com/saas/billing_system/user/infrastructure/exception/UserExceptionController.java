package com.saas.billing_system.user.infrastructure.exception;

import com.saas.billing_system.shared.dto.response.ApiExceptionDto;
import com.saas.billing_system.user.domain.exception.EmailException;
import com.saas.billing_system.user.domain.exception.PasswordException;
import com.saas.billing_system.user.domain.exception.PhoneNumberException;
import com.saas.billing_system.user.domain.exception.UserException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController {

  @ExceptionHandler(exception = UserException.class)
  public ResponseEntity<ApiExceptionDto> handleUserException(UserException exception) {
    return ResponseEntity.badRequest().body(ApiExceptionDto.badRequest(exception));
  }

  @ExceptionHandler(exception = PasswordException.class)
  public ResponseEntity<ApiExceptionDto> handlePasswordException(PasswordException exception) {
    return ResponseEntity.badRequest().body(ApiExceptionDto.badRequest(exception));
  }

  @ExceptionHandler(exception = EmailException.class)
  public ResponseEntity<ApiExceptionDto> handleEmailException(EmailException exception) {
    return ResponseEntity.badRequest().body(ApiExceptionDto.badRequest(exception));
  }

  @ExceptionHandler(exception = PhoneNumberException.class)
  public ResponseEntity<ApiExceptionDto> handlePhoneNumberException(PhoneNumberException exception) {
    return ResponseEntity.badRequest().body(ApiExceptionDto.badRequest(exception));
  }
}

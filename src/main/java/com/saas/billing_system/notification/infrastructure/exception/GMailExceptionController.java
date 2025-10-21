package com.saas.billing_system.notification.infrastructure.exception;

import com.saas.billing_system.notification.domain.exception.GMailException;
import com.saas.billing_system.shared.dto.response.ApiExceptionDto;

import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GMailExceptionController {

  @EventListener(GMailException.class)
  public ResponseEntity<ApiExceptionDto> handleGmailException(GMailException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiExceptionDto.create(exception));
  }

}

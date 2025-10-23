package com.saas.billing_system.shared.infrastructure.exception;

import com.saas.billing_system.shared.dto.response.ApiExceptionDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class htttpController {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiExceptionDto> handleNoHandlerFoundException(NoHandlerFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiExceptionDto.notFound("No url match found", "URI_NOT_FOUND"));
  }
}

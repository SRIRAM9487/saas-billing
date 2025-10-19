package com.saas.billing_system.shared.dto.response;

import java.time.LocalDateTime;

import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.exception.BaseException;

import org.springframework.http.HttpStatus;

public record ApiExceptionDto(
    HttpStatus status,
    String message,
    LocalDateTime timeStamp,
    String path) {

  public static ApiExceptionDto create(HttpStatus status, String message) {
    return new ApiExceptionDto(status, message, LocalDateTime.now(), UserContextHolder.getPath());
  }

  public static ApiExceptionDto create(HttpStatus status, BaseException exception) {
    return new ApiExceptionDto(status, exception.getMessage(), LocalDateTime.now(), UserContextHolder.getPath());
  }

  public static ApiExceptionDto badRequest(BaseException exception) {
    return create(HttpStatus.BAD_REQUEST, exception.getMessage());
  }

  public static ApiExceptionDto notFound(BaseException exception) {
    return create(HttpStatus.NOT_FOUND, exception);
  }

}

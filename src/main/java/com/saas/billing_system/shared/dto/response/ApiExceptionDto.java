package com.saas.billing_system.shared.dto.response;

import java.time.LocalDateTime;
import java.util.Optional;

import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.exception.BaseException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public record ApiExceptionDto(
    boolean success,
    HttpMethod method,
    HttpStatus status,
    LocalDateTime timeStamp,
    String path,
    String message,
    String code) {

  public static ApiExceptionDto create(HttpMethod method, HttpStatus status, String message, String code) {
    return new ApiExceptionDto(
        false,
        method,
        status,
        LocalDateTime.now(),
        UserContextHolder.getPath(),
        message,
        code);
  }

  public static ApiExceptionDto create(BaseException exception) {
    return new ApiExceptionDto(
        false,
        UserContextHolder.getMethod(),
        null,
        LocalDateTime.now(),
        Optional.ofNullable(UserContextHolder.getPath()).orElse("N/A"),
        exception.getMessage(),
        exception.getCode());
  }

  public static ApiExceptionDto create(BaseException exception, HttpStatus status) {
    return new ApiExceptionDto(
        false,
        UserContextHolder.getMethod(),
        status,
        LocalDateTime.now(),
        Optional.ofNullable(UserContextHolder.getPath()).orElse("N/A"),
        exception.getMessage(),
        exception.getCode());
  }

  public static ApiExceptionDto badRequest(BaseException exception) {
    return create(exception, HttpStatus.BAD_REQUEST);
  }

  public static ApiExceptionDto unAuthorized(BaseException exception) {
    return create(exception, HttpStatus.UNAUTHORIZED);
  }

  public static ApiExceptionDto forbidden(BaseException exception) {
    return create(exception, HttpStatus.FORBIDDEN);
  }

  public static ApiExceptionDto notFound(BaseException exception) {
    return create(exception, HttpStatus.NOT_FOUND);
  }

  public static ApiExceptionDto conflict(BaseException exception) {
    return create(exception, HttpStatus.CONFLICT);
  }

}

package com.saas.billing_system.shared.dto.response;

import java.time.LocalDateTime;
import java.util.Optional;

import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.exception.BaseException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public record ApiExceptionDto(
    boolean success,
    String method,
    HttpStatus status,
    String timeStamp,
    String path,
    String message,
    String code) {

  public static ApiExceptionDto create(HttpMethod method, HttpStatus status, String message, String code) {
    return new ApiExceptionDto(
        false,
        method.name(),
        status,
        LocalDateTime.now().toString(),
        UserContextHolder.getPath(),
        message,
        code);
  }

  public static ApiExceptionDto create(HttpStatus status, String message, String code) {
    return new ApiExceptionDto(
        false,
        UserContextHolder.getMethod().name(),
        status,
        LocalDateTime.now().toString(),
        UserContextHolder.getPath(),
        message,
        code);
  }

  public static ApiExceptionDto create(BaseException exception) {
    return new ApiExceptionDto(
        false,
        UserContextHolder.getMethod().name(),
        null,
        LocalDateTime.now().toString(),
        Optional.ofNullable(UserContextHolder.getPath()).orElse("N/A"),
        exception.getMessage(),
        exception.getCode());
  }

  public static ApiExceptionDto create(BaseException exception, HttpStatus status) {
    return new ApiExceptionDto(
        false,
        UserContextHolder.getMethod().name(),
        status,
        LocalDateTime.now().toString(),
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

  public static ApiExceptionDto badRequest(String message, String code) {
    return create(HttpStatus.BAD_REQUEST, message, code);
  }

  public static ApiExceptionDto unAuthorized(String message, String code) {
    return create(HttpStatus.UNAUTHORIZED, message, code);
  }

  public static ApiExceptionDto forbidden(String message, String code) {
    return create(HttpStatus.FORBIDDEN, message, code);
  }

  public static ApiExceptionDto notFound(String message, String code) {
    return create(HttpStatus.NOT_FOUND, message, code);
  }

  public static ApiExceptionDto conflict(String message, String code) {
    return create(HttpStatus.CONFLICT, message, code);
  }

}

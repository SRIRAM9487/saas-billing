package com.saas.billing_system.shared.dto.response;

import java.time.LocalDateTime;

public record ApiResponseDto<T>(
    boolean success,
    T data,
    LocalDateTime timeStamp) {

  public static <T> ApiResponseDto<T> create(T data) {
    return new ApiResponseDto<>(true, data, LocalDateTime.now());
  }

}

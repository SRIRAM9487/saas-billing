package com.saas.billing_system.shared.dto.response;

import java.time.LocalDateTime;

import com.saas.billing_system.shared.domain.SoftDelete;

public record ApiResponseDto<T extends SoftDelete>(
    boolean success,
    T data,
    LocalDateTime timeStamp) {

  public static ApiResponseDto<SoftDelete> create(SoftDelete data) {
    return new ApiResponseDto<SoftDelete>(true, data, LocalDateTime.now());
  }
}

package com.saas.billing_system.shared.dto.response;

public record ApiResponseDto<T>(
    T obj,
    String timeStamp,
    String payload) {

}

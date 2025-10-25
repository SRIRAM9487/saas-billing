package com.saas.billing_system.tenant.infrastructure.dto.request;

import com.saas.billing_system.user.infrastructure.dto.request.UserUpdateRequestDto;

public record TenantUpdateRequestDto(
    String name,
    int currency,
    String addressLine1,
    String addressLine2,
    String city,
    String district,
    String state,
    String postalCode,
    String country,
    UserUpdateRequestDto userRequestDto) {


}

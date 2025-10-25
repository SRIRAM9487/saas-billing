package com.saas.billing_system.plan.infrastructure.dto.request.feature;

import java.util.UUID;


public record FeatureUpdateRequestDto(
     String id,
    String name,
    String description,
    long rateLimit,
    UUID plan) {

}

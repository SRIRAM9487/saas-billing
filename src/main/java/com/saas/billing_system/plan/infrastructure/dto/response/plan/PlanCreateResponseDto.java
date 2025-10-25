package com.saas.billing_system.plan.infrastructure.dto.response.plan;

public record PlanCreateResponseDto(
    String name,
    String description,
    long rateLimit,
    long currentUsage,
    String planId) {
}

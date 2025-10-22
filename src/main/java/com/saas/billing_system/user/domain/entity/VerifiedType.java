package com.saas.billing_system.user.domain.entity;

public enum VerifiedType {
  PENDING,
  VERIFIED;

  public boolean isVerified() {
    return this == VERIFIED;
  }
  public boolean isPending() {
    return this == PENDING;
  }
}

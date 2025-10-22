package com.saas.billing_system.user.domain.entity;

public enum UserRole {
  SUDO,
  ADMIN,
  SYSTEM,
  TENANT;

  public boolean isSudo() {
    return this == SUDO;
  }

  public boolean isAdmin() {
    return this == ADMIN;
  }
  public boolean isSystem() {
    return this == SYSTEM;
  }

  public boolean isTenant() {
    return this == TENANT;
  }

}

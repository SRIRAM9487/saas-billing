package com.saas.billing_system.user.domain.vo;

import java.util.UUID;

public record UserId(UUID id) {

  public static UserId create(){
    return new UserId(UUID.randomUUID());
  }
}

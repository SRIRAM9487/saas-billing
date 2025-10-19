package com.saas.billing_system.shared.domain;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class SoftDelete {

  private LocalDateTime deletedAt;

  private LocalDateTime updatedAt;

  private LocalDateTime createdAt;

  private boolean active;

}

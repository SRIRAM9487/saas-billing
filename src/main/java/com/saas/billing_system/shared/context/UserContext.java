package com.saas.billing_system.shared.context;

import java.util.UUID;

import org.springframework.http.HttpMethod;

import lombok.Builder;

@Builder
public class UserContext {
  private UUID userId;
  private UUID tenantId;
  private String path;
  private HttpMethod method;
  public UUID getUserId() {
    return userId;
  }
  public void setUserId(UUID userId) {
    this.userId = userId;
  }
  public UUID getTenantId() {
    return tenantId;
  }
  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public HttpMethod getMethod() {
    return method;
  }
  public void setMethod(HttpMethod method) {
    this.method = method;
  }

}

package com.saas.billing_system.shared.context;

import java.util.UUID;

import org.springframework.http.HttpMethod;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserContext {
  private UUID userId;
  private String path;
  private HttpMethod method;

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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

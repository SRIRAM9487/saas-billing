package com.saas.billing_system.shared.context;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserContext {

  private final Logger log = LoggerFactory.getLogger(UserContext.class);

  private UUID userId;
  private String path;
  private HttpMethod method;

  public UUID getUserId() {
    return userId;
  }

  public String getPath() {
    return path;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setUserId(UUID userId) {
    log.trace("User Context updated");
    log.trace("User id {}", userId);
    this.userId = userId;
  }

}

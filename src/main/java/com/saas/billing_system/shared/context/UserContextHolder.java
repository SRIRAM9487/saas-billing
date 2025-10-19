package com.saas.billing_system.shared.context;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

public class UserContextHolder {

  private static final Logger log = LoggerFactory.getLogger(UserContextHolder.class);

  private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

  public static void set(UserContext context) {
    log.debug("User Context initialized");
    log.trace(context.toString());
    CONTEXT.set(context);
  }

  public static UserContext get() {
    return CONTEXT.get();
  }

  public static void clear() {
    CONTEXT.remove();
  }

  public static UUID getUserId() {
    return CONTEXT.get().getUserId();
  }

  public static HttpMethod getMethod() {
    return CONTEXT.get().getMethod();
  }

  public static String getPath() {
    return CONTEXT.get().getPath();
  }

}

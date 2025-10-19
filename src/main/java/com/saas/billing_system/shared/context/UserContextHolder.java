package com.saas.billing_system.shared.context;

import java.util.UUID;

import org.springframework.http.HttpMethod;

public class UserContextHolder {

  private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

  public static void set(UserContext context) {
    CONTEXT.set(context);
  }

  public static void get() {
    CONTEXT.get();
  }

  public static void clear() {
    CONTEXT.remove();
  }

  public static UUID getUserId() {
    return CONTEXT.get().getUserId();
  }

  public static UUID getTenantId() {
    return CONTEXT.get().getTenantId();
  }

  public static HttpMethod getMethod() {
    return CONTEXT.get().getMethod();
  }

  public static String getPath() {
    return CONTEXT.get().getPath();
  }

}

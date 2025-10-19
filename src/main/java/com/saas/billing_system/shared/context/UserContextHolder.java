package com.saas.billing_system.shared.context;

import java.util.UUID;

public class UserContextHolder {

  private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

  public static void set(UserContext context) {
    CONTEXT.set(context);
  }

  public static void get() {
    CONTEXT.get();
  }

  public static void clear(){
    CONTEXT.remove();
  }

  public static UUID getUserId(){
    return CONTEXT.get().getUserId();
  }

  public static UUID getTenantId(){
    return CONTEXT.get().getTenantId();
  }
  public static String getPath(){
    return CONTEXT.get().getPath();
  }

}

package com.saas.billing_system.shared.constant;

public class RequestConstant {

  public static String[] getPaths = {
      "/user/server",
      "/user/email/verify",
      "/user/login",
      "/user/ui/login",
      "/user/ui/register" };

  public static String[] postPaths = { "/user/register", "/user/login", "/user/login/verify" };

  public static String[] patchPaths = { "/user/email/verify" };

}

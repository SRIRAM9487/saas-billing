package com.saas.billing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingSystemApplication {

  @Autowired

  public static void main(String[] args) {
     SpringApplication.run(BillingSystemApplication.class, args);

  }

}

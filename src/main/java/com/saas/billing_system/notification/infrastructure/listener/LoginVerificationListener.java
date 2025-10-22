package com.saas.billing_system.notification.infrastructure.listener;

import com.saas.billing_system.user.application.event.LoginVerificationEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginVerificationListener {

  private final Logger log = LoggerFactory.getLogger(LoginVerificationListener.class);
  private final JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String from;

  @EventListener
  @Async
  public void sendVerificationOtp(LoginVerificationEvent verificationEvent) {
    String email = verificationEvent.getEmail();
    String otp = verificationEvent.getOtp();
    log.trace("Login Verification being generated for user {}", email);

    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom(from);
    mail.setTo(email);
    mail.setSubject("Login Verification");
    String message = """
        Hello,

        We received a request to verify a login to your account.

        Please use the following One-Time Password (OTP) to complete your login:

        🔸 OTP: %s

        This OTP is valid for the next 10 minutes.
        Do not share it with anyone for your account's safety.

        If you did not attempt to log in, please ignore this email or contact support immediately.

        Best regards,
        The Billing System Security Team
        """.formatted(otp);

    mail.setText(message);

    javaMailSender.send(mail);
  }
}

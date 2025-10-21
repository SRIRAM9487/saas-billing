package com.saas.billing_system.notification.infrastructure.listener;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.saas.billing_system.notification.domain.exception.GMailException;
import com.saas.billing_system.user.application.event.EmailVerificationEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailVerificationListener {

  private final Logger log = LoggerFactory.getLogger(EmailVerificationListener.class);
  private final JavaMailSender javaMailSender;
  private final String from = "sriram9487tk@gmail.com";

  @EventListener
  public void sendVerificationEmail(EmailVerificationEvent event) {
    String email = event.getEmail();
    String userId = event.getUserId();
    String verificationToken = event.getVerificationToken();

    log.debug("Email Verfication for user {}", email);

    ClassPathResource resource = new ClassPathResource("templates/notification/email-verification.html");

    try {
      log.trace("Html template is beign loaded");
      String htmlTemplate = Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);

      String htmlContent = htmlTemplate.replace("https://yourapp.com/verify?token=dummy123",
          "http://localhost:8080/user/email/verify?token=" + verificationToken);

      log.trace("Html template is  loaded");
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

      messageHelper.setFrom(from);
      messageHelper.setTo(email);
      messageHelper.setSubject("Email Verification");
      messageHelper.setText(htmlContent, true);

      javaMailSender.send(message);
      log.trace("Verification Email Sent for {}", userId);
    } catch (Exception e) {
      log.error("Error : {}",e.toString());
      throw GMailException.failed();
    }
  }

}

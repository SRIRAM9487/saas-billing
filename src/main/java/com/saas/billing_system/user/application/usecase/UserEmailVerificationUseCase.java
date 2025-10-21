package com.saas.billing_system.user.application.usecase;

import java.util.UUID;

import com.saas.billing_system.user.application.event.EmailVerificationEvent;
import com.saas.billing_system.user.application.service.RedisEmailService;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.EmailException;
import com.saas.billing_system.user.domain.exception.UserException;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEmailVerificationUseCase {

  private final Logger log = LoggerFactory.getLogger(UserEmailVerificationUseCase.class);
  private final UserRepository userRepo;
  private final RedisEmailService redisEmailService;
  private final ApplicationEventPublisher applicationEventPublisher;

  public User verifyEmail(String email, String token) {

    log.debug("Email verification for user {}", email);

    User user = userRepo.findByEmail_Value(email).orElseThrow(() -> {
      log.trace("User not found for email : {}", email);
      return UserException.notFound(email);
    });

    String otp = redisEmailService.getOtp(email);

    if (!verifyOtp(email, otp))
      throw EmailException.verificationFailedInvalidOtp(email);

    user.verifyEmail();
    userRepo.save(user);
    log.debug("Email Verified : {}", email);
    return user;
  }

  public User generateEmailVerification(String email) {

    User unverifiedUser = userRepo.findByEmail_Value(email).orElseThrow(() -> {
      log.trace("User not found for email : {}", email);
      return UserException.notFound(email);
    });

    applicationEventPublisher.publishEvent(new EmailVerificationEvent(this, email,
        unverifiedUser.getUserName(), this.generateVerificationToken(email)));


    return unverifiedUser;
  }

  public String generateVerificationToken(String email) {
    log.trace("generating verification token for user");
    StringBuilder otp = new StringBuilder();
    otp.append(UUID.randomUUID().toString().replace("-", ""));
    redisEmailService.saveOtp(email, otp.toString());
    return otp.toString();
  }

  private boolean verifyOtp(String userId, String otp) {
    log.debug("verifing otp for user {}", userId);
    String cache = redisEmailService.getOtp(userId);
    if (cache.equals(otp)) {
      log.trace("Otp verification successfull");
      redisEmailService.remove(userId);
      return true;
    }
    log.trace("Otp verification failed");
    return false;
  }

}

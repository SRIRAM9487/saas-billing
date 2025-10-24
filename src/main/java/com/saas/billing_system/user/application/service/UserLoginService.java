package com.saas.billing_system.user.application.service;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.vo.Email;
import com.saas.billing_system.user.domain.vo.UserId;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService {

  private final RedisEmailService redisEmailService;
  private final Logger log = LoggerFactory.getLogger(UserLoginService.class);

  public String generateVerificationToken(String email) {
    log.trace("generating verification token for user");
    StringBuilder otp = new StringBuilder();
    otp.append(UUID.randomUUID().toString().replace("-", ""));
    redisEmailService.saveOtp(email, otp.toString());
    return otp.toString();
  }

  public String generateLoginOtp(String email) {
    log.trace("generating verification token for user");
    StringBuilder otp = new StringBuilder();
    otp.append(new DecimalFormat("000000").format(new Random().nextInt(999999)));
    redisEmailService.saveOtp(email, otp.toString());
    return otp.toString();
  }

  public boolean verifyOtp(String userId, String otp) {
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

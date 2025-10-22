package com.saas.billing_system.user.application.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisLoginService {

  private final RedisTemplate<String, String> redisTemplate;

  public RedisLoginService(@Qualifier("string") RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void saveOtp(String key, String value) {
    redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(10));
  }

  public String getOtp(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public void remove(String key) {
    redisTemplate.delete(key);
  }

}

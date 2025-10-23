package com.saas.billing_system.user.application.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  Logger LOG = LoggerFactory.getLogger(JwtService.class);

  public String generate(User user) {
    return this.generate(user.getId().id());
  }

  public String generate(UUID userId) {

    LOG.debug("Generating token for id {}", userId);
    Map<String, Object> claims = new HashMap<>();
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime limit = now.toLocalDate().plusDays(1).atStartOfDay();
    Date issuedAt = new Date();
    Date expiration = Date.from(limit.atZone(ZoneId.systemDefault()).toInstant());

    String token = Jwts
        .builder()
        .claims()
        .add(claims)
        .subject(userId.toString())
        .issuedAt(issuedAt)
        .expiration(expiration)
        .and()
        .signWith(getKey())
        .compact();
    LOG.trace("Token expires in {}", expiration);

    LOG.debug("Token generated for id {}", userId);
    return token;
  }

  public boolean validate(String token, UUID userId) {
    String tokenUserId = extractUserId(token);
    LOG.trace("Validating token : {} user : {}",token,userId);
    boolean valid =(tokenUserId.equals(userId.toString()) && !isTokenExpired(token));
      LOG.trace("Is Token Valid : {}",valid);
    return valid;
  }

  private SecretKey getKey() {
    LOG.trace("Invoking the getKey()");
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUserId(String token) {
    LOG.trace("Invoking the extractUserId()");
    String userId = extractClaim(token, Claims::getSubject);
    LOG.trace("Token User id : {}", userId);
    return userId;
  }

  public boolean isTokenExpired(String token) {
    LOG.trace("Invoking the isTokenExpired()");
    boolean expired = extractExpiration(token).before(new Date());
    LOG.trace("Token expired : {}", expired);
    return expired;
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}

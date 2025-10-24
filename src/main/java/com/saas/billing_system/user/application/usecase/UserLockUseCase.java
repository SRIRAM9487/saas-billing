package com.saas.billing_system.user.application.usecase;

import com.saas.billing_system.user.application.service.UserFactory;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.UserException;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLockUseCase {

  private final UserFactory userFactory;
  private final UserRepository userRepo;
  private final Logger log = LoggerFactory.getLogger(UserLockUseCase.class);

  public User toggleLock(String userId) {

    User exisitngUser = userFactory.findUserById(userId).orElseThrow(() -> {
      log.trace("User not found : {}", userId);
      return UserException.notFound(userId);
    });

    log.trace("is account locked : {}", !exisitngUser.isAccountNonLocked());
    exisitngUser.toggleLock();
    log.trace("is account locked : {}", !exisitngUser.isAccountNonLocked());

    User user = userRepo.save(exisitngUser);
    log.trace("User status updated {}", user.getId().id());

    return user;

  }

}

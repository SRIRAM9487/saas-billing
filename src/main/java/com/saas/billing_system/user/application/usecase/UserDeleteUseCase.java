package com.saas.billing_system.user.application.usecase;

import com.saas.billing_system.user.application.dto.request.UserDeleteRequestDto;
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
public class UserDeleteUseCase {

  private final Logger log = LoggerFactory.getLogger(UserDeleteUseCase.class);
  private final UserRepository userRepo;
  private final UserFactory userFactory;

  public User deleteUser(UserDeleteRequestDto requestDto) {

    log.debug("Deleting user");
    log.debug("User Id : {}", requestDto.userId());

    User user = userFactory.findUserById(requestDto.userId()).orElseThrow(() -> {
      log.trace("User {} not found");
      return UserException.notFound(requestDto.userId());
    });

    User deletedUser = userRepo.save(user);
    log.trace("User deleted");
    return deletedUser;
  }

}

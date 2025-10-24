package com.saas.billing_system.user.infrastructure.persistence;

import java.util.Optional;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.vo.UserId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {


  Optional<User> findByUserName(String name);

  Optional<User> findByEmail_Value(String name);

}

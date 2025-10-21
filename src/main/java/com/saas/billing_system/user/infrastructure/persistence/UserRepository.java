package com.saas.billing_system.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import com.saas.billing_system.user.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByUserName(String name);

  Optional<User> findByEmail_Value(String name);

}

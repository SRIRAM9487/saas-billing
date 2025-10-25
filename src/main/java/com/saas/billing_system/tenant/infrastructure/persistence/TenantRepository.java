package com.saas.billing_system.tenant.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.domain.vo.TenantId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, TenantId> {

  Optional<Tenant> findTenantByUser(UUID userId);
}

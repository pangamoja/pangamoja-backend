package com.pangamoja.repo;

import com.pangamoja.domain.Entitlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntitlementRepository extends JpaRepository<Entitlement, String> {
    Optional<Entitlement> findByTenantIdAndModuleKey(String tenantId, String moduleKey);
    List<Entitlement> findAllByTenantId(String tenantId);
}

package com.pangamoja.repo;

import com.pangamoja.domain.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StructureRepository extends JpaRepository<Structure, String> {

    List<Structure> findAllByTenantIdOrderByCreatedAtDesc(String tenantId);

    Optional<Structure> findByIdAndTenantId(String id, String tenantId);
}

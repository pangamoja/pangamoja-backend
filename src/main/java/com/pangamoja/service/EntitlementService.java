package com.pangamoja.service;

import com.pangamoja.config.TenantContext;
import com.pangamoja.domain.Entitlement;
import com.pangamoja.domain.EntitlementStatus;
import com.pangamoja.repo.EntitlementRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class EntitlementService {

    private final EntitlementRepository entitlementRepo;

    public EntitlementService(EntitlementRepository entitlementRepo) {
        this.entitlementRepo = entitlementRepo;
    }

    public List<Entitlement> getCurrentTenantEntitlements() {
        String tenantId = requireTenant();
        return entitlementRepo.findAllByTenantId(tenantId);
    }

    /**
     * MVP0: simple gatekeeper for module endpoints.
     * Call this at the start of every /m1/* request.
     */
    public void requireModuleEnabled(String moduleKey) {
        String tenantId = requireTenant();

        Entitlement ent = entitlementRepo.findByTenantIdAndModuleKey(tenantId, moduleKey)
                .orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "MODULE_LOCKED"));

        if (ent.getStatus() == EntitlementStatus.LOCKED) {
            throw new ResponseStatusException(FORBIDDEN, "MODULE_LOCKED");
        }

        if (ent.getStatus() == EntitlementStatus.TRIAL) {
            Instant endsAt = ent.getTrialEndsAt();
            if (endsAt == null || endsAt.isBefore(Instant.now())) {
                throw new ResponseStatusException(FORBIDDEN, "TRIAL_EXPIRED");
            }
        }
        // ACTIVE -> ok
    }

    private static String requireTenant() {
        String tenantId = TenantContext.getTenantId();
        if (tenantId == null || tenantId.isBlank()) {
            // Normally TenantFilter prevents this, but keep it safe.
            throw new ResponseStatusException(FORBIDDEN, "NO_TENANT_CONTEXT");
        }
        return tenantId;
    }
}

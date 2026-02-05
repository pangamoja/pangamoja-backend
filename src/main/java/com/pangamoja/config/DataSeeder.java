package com.pangamoja.config;

import com.pangamoja.domain.Entitlement;
import com.pangamoja.domain.EntitlementStatus;
import com.pangamoja.domain.PlanType;
import com.pangamoja.domain.Tenant;
import com.pangamoja.domain.TenantType;
import com.pangamoja.repo.EntitlementRepository;
import com.pangamoja.repo.TenantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class DataSeeder implements CommandLineRunner {

    private final TenantRepository tenantRepo;
    private final EntitlementRepository entitlementRepo;

    public DataSeeder(TenantRepository tenantRepo, EntitlementRepository entitlementRepo) {
        this.tenantRepo = tenantRepo;
        this.entitlementRepo = entitlementRepo;
    }

    @Override
    public void run(String... args) {
        // Seed tenant-vater
        tenantRepo.findById("tenant-vater").orElseGet(() ->
                tenantRepo.save(new Tenant("tenant-vater", "Vater Operator", TenantType.OPERATOR))
        );

        // Seed Entitlement for Module 1 (Milk Ops)
        entitlementRepo.findByTenantIdAndModuleKey("tenant-vater", Entitlement.MODULE_M1_MILK_OPS)
                .orElseGet(() -> entitlementRepo.save(
                        new Entitlement(
                                "tenant-vater",
                                Entitlement.MODULE_M1_MILK_OPS,
                                EntitlementStatus.TRIAL,
                                Instant.now().plus(Duration.ofDays(30)),
                                PlanType.FAMILY,
                                0
                        )
                ));
    }
}

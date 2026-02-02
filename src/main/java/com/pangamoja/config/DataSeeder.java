package com.pangamoja.config;

import com.pangamoja.domain.Tenant;
import com.pangamoja.domain.TenantType;
import com.pangamoja.repo.TenantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final TenantRepository tenantRepo;

    public DataSeeder(TenantRepository tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    @Override
    public void run(String... args) {
        // Seed tenant-vater
        tenantRepo.findById("tenant-vater").orElseGet(() ->
                tenantRepo.save(new Tenant("tenant-vater", "Vater Operator", TenantType.OPERATOR))
        );
    }
}

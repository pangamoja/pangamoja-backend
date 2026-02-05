package com.pangamoja.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "ENTITLEMENTS",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_entitlement_tenant_module", columnNames = {"tenantId", "moduleKey"})
        },
        indexes = {
                @Index(name = "idx_entitlement_tenant", columnList = "tenantId")
        }
)
public class Entitlement {

    /**
     * Module keys (stabil, niemals ändern).
     *
     * MVP0: nur Modul #1.
     */
    public static final String MODULE_M1_MILK_OPS = "m1_milk_ops";

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(length = 64, nullable = false)
    private String tenantId;

    @Column(length = 64, nullable = false)
    private String moduleKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntitlementStatus status;

    /**
     * Nur relevant, wenn status == TRIAL
     */
    private Instant trialEndsAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType plan;

    /**
     * Optionaler Preis-Override (z.B. FAMILY = 0).
     */
    private Integer priceOverrideCents;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected Entitlement() {
        // JPA
    }

    public Entitlement(
            String tenantId,
            String moduleKey,
            EntitlementStatus status,
            Instant trialEndsAt,
            PlanType plan,
            Integer priceOverrideCents
    ) {
        this.id = UUID.randomUUID().toString();
        this.tenantId = tenantId;
        this.moduleKey = moduleKey;
        this.status = status;
        this.trialEndsAt = trialEndsAt;
        this.plan = plan;
        this.priceOverrideCents = priceOverrideCents;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public EntitlementStatus getStatus() {
        return status;
    }

    public Instant getTrialEndsAt() {
        return trialEndsAt;
    }

    public PlanType getPlan() {
        return plan;
    }

    public Integer getPriceOverrideCents() {
        return priceOverrideCents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setStatus(EntitlementStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }

    public void setTrialEndsAt(Instant trialEndsAt) {
        this.trialEndsAt = trialEndsAt;
        this.updatedAt = Instant.now();
    }

    public void setPlan(PlanType plan) {
        this.plan = plan;
        this.updatedAt = Instant.now();
    }

    public void setPriceOverrideCents(Integer priceOverrideCents) {
        this.priceOverrideCents = priceOverrideCents;
        this.updatedAt = Instant.now();
    }
}

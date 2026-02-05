package com.pangamoja.web.dto;

import com.pangamoja.domain.Entitlement;
import com.pangamoja.domain.EntitlementStatus;
import com.pangamoja.domain.PlanType;

import java.time.Instant;

public class EntitlementItem {
    private final String moduleKey;
    private final EntitlementStatus status;
    private final Instant trialEndsAt;
    private final PlanType plan;
    private final Integer priceOverrideCents;

    public EntitlementItem(String moduleKey, EntitlementStatus status, Instant trialEndsAt, PlanType plan, Integer priceOverrideCents) {
        this.moduleKey = moduleKey;
        this.status = status;
        this.trialEndsAt = trialEndsAt;
        this.plan = plan;
        this.priceOverrideCents = priceOverrideCents;
    }

    public static EntitlementItem from(Entitlement ent) {
        return new EntitlementItem(
                ent.getModuleKey(),
                ent.getStatus(),
                ent.getTrialEndsAt(),
                ent.getPlan(),
                ent.getPriceOverrideCents()
        );
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
}

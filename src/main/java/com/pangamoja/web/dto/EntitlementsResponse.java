package com.pangamoja.web.dto;

import java.util.List;

public class EntitlementsResponse {
    private final String tenantId;
    private final List<EntitlementItem> entitlements;

    public EntitlementsResponse(String tenantId, List<EntitlementItem> entitlements) {
        this.tenantId = tenantId;
        this.entitlements = entitlements;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<EntitlementItem> getEntitlements() {
        return entitlements;
    }
}

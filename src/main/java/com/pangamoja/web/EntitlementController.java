package com.pangamoja.web;

import com.pangamoja.config.TenantContext;
import com.pangamoja.service.EntitlementService;
import com.pangamoja.web.dto.EntitlementItem;
import com.pangamoja.web.dto.EntitlementsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntitlementController {

    private final EntitlementService entitlementService;

    public EntitlementController(EntitlementService entitlementService) {
        this.entitlementService = entitlementService;
    }

    @GetMapping("/entitlements")
    public EntitlementsResponse list() {
        String tenantId = TenantContext.getTenantId();

        List<EntitlementItem> items = entitlementService.getCurrentTenantEntitlements()
                .stream()
                .map(EntitlementItem::from)
                .toList();

        return new EntitlementsResponse(tenantId, items);
    }
}

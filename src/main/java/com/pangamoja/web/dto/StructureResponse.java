package com.pangamoja.web.dto;

import com.pangamoja.domain.Structure;
import com.pangamoja.domain.StructureStatus;
import com.pangamoja.domain.StructureType;

import java.time.Instant;

public class StructureResponse {

    private String id;
    private String tenantId;
    private StructureType type;
    private String name;
    private StructureStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public static StructureResponse from(Structure s) {
        StructureResponse r = new StructureResponse();
        r.id = s.getId();
        r.tenantId = s.getTenantId();
        r.type = s.getType();
        r.name = s.getName();
        r.status = s.getStatus();
        r.createdAt = s.getCreatedAt();
        r.updatedAt = s.getUpdatedAt();
        return r;
    }

    public String getId() { return id; }
    public String getTenantId() { return tenantId; }
    public StructureType getType() { return type; }
    public String getName() { return name; }
    public StructureStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}

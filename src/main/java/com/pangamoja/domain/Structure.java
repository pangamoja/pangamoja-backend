package com.pangamoja.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "structures",
        indexes = @Index(name = "idx_structures_tenant", columnList = "tenantId")
)
public class Structure {

    @Id
    private String id;

    @Column(nullable = false)
    private String tenantId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StructureType type;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StructureStatus status = StructureStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    protected Structure() {
        // JPA
    }

    public Structure(String tenantId, StructureType type, String name) {
        this.id = UUID.randomUUID().toString();
        this.tenantId = tenantId;
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public StructureType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public StructureStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setStatus(StructureStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }
}

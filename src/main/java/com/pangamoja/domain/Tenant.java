package com.pangamoja.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "TENANTS")
public class Tenant {

    @Id
    @Column(length = 64, nullable = false)
    private String id; // z.B. "tenant-vater"

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenantType type;

    @Column(nullable = false)
    private Instant createdAt;

    protected Tenant() {}

    public Tenant(String id, String name, TenantType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public TenantType getType() { return type; }
    public Instant getCreatedAt() { return createdAt; }
}

package com.pangamoja.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pangamoja.repo.TenantRepository;
import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {


    private final TenantRepository tenantRepo;


    public TenantFilter(TenantRepository tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    public static final String TENANT_HEADER = "X-Tenant-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Diese Pfade sollen ohne Tenant-Header funktionieren
        if (path.startsWith("/health")
                || path.startsWith("/h2")
                || path.startsWith("/error")
                || path.startsWith("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tenantId = request.getHeader(TENANT_HEADER);

        if (tenantId == null || tenantId.isBlank()) {
            response.setStatus(400);
            response.getWriter().write("Missing header: " + TENANT_HEADER);
            return;
        }

        tenantId = tenantId.trim();

        if (!tenantRepo.existsById(tenantId)) {
            response.setStatus(404);
            response.getWriter().write("Unknown tenant: " + tenantId);
            return;
        }

        try {
            TenantContext.setTenantId(tenantId);
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}

package com.pangamoja.web;

import com.pangamoja.config.TenantContext;
import com.pangamoja.domain.Structure;
import com.pangamoja.repo.StructureRepository;
import com.pangamoja.web.dto.CreateStructureRequest;
import com.pangamoja.web.dto.StructureResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/structures")
public class StructureController {

    private final StructureRepository repo;

    public StructureController(StructureRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StructureResponse create(@Valid @RequestBody CreateStructureRequest req) {
        String tenantId = TenantContext.getTenantId();

        Structure s = new Structure(tenantId, req.getType(), req.getName());
        Structure saved = repo.save(s);

        return StructureResponse.from(saved);
    }

    @GetMapping
    public List<StructureResponse> list() {
        String tenantId = TenantContext.getTenantId();

        return repo.findAllByTenantIdOrderByCreatedAtDesc(tenantId)
                .stream()
                .map(StructureResponse::from)
                .toList();
    }
}

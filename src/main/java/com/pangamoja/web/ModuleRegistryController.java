package com.pangamoja.web;

import com.pangamoja.domain.Entitlement;
import com.pangamoja.web.dto.ModuleDefinition;
import com.pangamoja.web.dto.ModuleRegistryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ModuleRegistryController {

    @GetMapping("/module-registry")
    public ModuleRegistryResponse registry() {
        // MVP0: registry is static (code), not stored in DB.
        return new ModuleRegistryResponse(
                "1",
                List.of(
                        new ModuleDefinition(
                                Entitlement.MODULE_M1_MILK_OPS,
                                "Milk Ops – Daily Transparency",
                                "Tägliche Wahrheit über Milch, Geld & Bestand",
                                "operations",
                                "/m1",
                                "/m/m1_milk_ops",
                                "clipboard"
                        )
                )
        );
    }
}

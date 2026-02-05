package com.pangamoja.web.dto;

import java.util.List;

public class ModuleRegistryResponse {
    private final String version;
    private final List<ModuleDefinition> modules;

    public ModuleRegistryResponse(String version, List<ModuleDefinition> modules) {
        this.version = version;
        this.modules = modules;
    }

    public String getVersion() {
        return version;
    }

    public List<ModuleDefinition> getModules() {
        return modules;
    }
}

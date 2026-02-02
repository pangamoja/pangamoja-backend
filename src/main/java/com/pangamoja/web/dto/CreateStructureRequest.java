package com.pangamoja.web.dto;

import com.pangamoja.domain.StructureType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateStructureRequest {

    @NotNull
    private StructureType type;

    @NotBlank
    private String name;

    public StructureType getType() {
        return type;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

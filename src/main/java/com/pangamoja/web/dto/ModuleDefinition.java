package com.pangamoja.web.dto;

public class ModuleDefinition {
    private final String key;
    private final String displayName;
    private final String tagline;
    private final String category;
    private final String apiBasePath;
    private final String uiHomeRoute;
    private final String icon;

    public ModuleDefinition(String key, String displayName, String tagline, String category, String apiBasePath, String uiHomeRoute, String icon) {
        this.key = key;
        this.displayName = displayName;
        this.tagline = tagline;
        this.category = category;
        this.apiBasePath = apiBasePath;
        this.uiHomeRoute = uiHomeRoute;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTagline() {
        return tagline;
    }

    public String getCategory() {
        return category;
    }

    public String getApiBasePath() {
        return apiBasePath;
    }

    public String getUiHomeRoute() {
        return uiHomeRoute;
    }

    public String getIcon() {
        return icon;
    }
}

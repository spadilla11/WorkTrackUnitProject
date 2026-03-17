package org.example.worktrack.enums;

public enum ProjectStatus {
    Planned("Planned"),
    InProgress("InProgress"),
    Completed("Completed");

    private final String displayName;

    ProjectStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}

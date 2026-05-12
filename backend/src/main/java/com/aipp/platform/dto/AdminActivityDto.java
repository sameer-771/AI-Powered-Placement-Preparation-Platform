package com.aipp.platform.dto;

import java.time.Instant;

public class AdminActivityDto {
    private Long id;
    private String actionType;
    private String actionDetails;
    private Instant createdAt;

    public AdminActivityDto(Long id, String actionType, String actionDetails, Instant createdAt) {
        this.id = id;
        this.actionType = actionType;
        this.actionDetails = actionDetails;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(String actionDetails) {
        this.actionDetails = actionDetails;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

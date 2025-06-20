package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class WebhookSubscriptionDTO {
    private UUID id;
    private String url;
    private EventType eventType;
    private boolean enabled;
    private LocalDateTime createdAt;

    // Getters and Setters

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public EventType getEventType() {
        return eventType;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

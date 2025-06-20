package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fimepay.merchantapp.model.EventType;

public class UpdateWebhookSubscriptionRequest {

    @NotBlank
    private String url;

    @NotNull
    private EventType eventType;

    private boolean enabled;

    // Getters and Setters

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
}

package com.fimepay.merchantapp.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ApiKeyDTO {
    private UUID id;
    private String name;
    private String keyValue;
    private List<String> scopes;
    private Integer rateLimit;
    private boolean revoked;
    private LocalDateTime createdAt;

    // Getters and Setters

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKeyValue() {
        return keyValue;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
    public List<String> getScopes() {
        return scopes;
    }
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
    public Integer getRateLimit() {
        return rateLimit;
    }
    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }
    public boolean isRevoked() {
        return revoked;
    }
    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

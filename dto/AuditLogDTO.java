package com.fimepay.merchantapp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditLogDTO {
    private UUID id;
    private String actor;
    private String action;
    private String details;
    private LocalDateTime timestamp;
    // getters/setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getActor() { return actor; }
    public void setActor(String actor) { this.actor = actor; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
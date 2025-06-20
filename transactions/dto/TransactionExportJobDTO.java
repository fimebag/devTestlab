package com.fimepay.merchantapp.transactions.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionExportJobDTO {
    private UUID id;
    private String filterJson;
    private String scheduleCron;
    private LocalDateTime lastRun;
    private String destination;
    // Getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFilterJson() {
        return filterJson;
    }
    public void setFilterJson(String filterJson) {
        this.filterJson = filterJson;
    }
    public String getScheduleCron() {
        return scheduleCron;
    }
    public void setScheduleCron(String scheduleCron) {
        this.scheduleCron = scheduleCron;
    }
    public LocalDateTime getLastRun() {
        return lastRun;
    }
    public void setLastRun(LocalDateTime lastRun) {
        this.lastRun = lastRun;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
}

package com.fimepay.merchantapp.transactions.dto;


import com.fimepay.merchantapp.transactions.model.AlertChannel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionAlertDTO {
    private UUID id;
    private BigDecimal thresholdAmount;
    private String criteriaJson;
    private AlertChannel channel;
    private LocalDateTime createdAt;
    // Getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }
    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }
    public String getCriteriaJson() {
        return criteriaJson;
    }
    public void setCriteriaJson(String criteriaJson) {
        this.criteriaJson = criteriaJson;
    }
    public AlertChannel getChannel() {
        return channel;
    }
    public void setChannel(AlertChannel channel) {
        this.channel = channel;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

    

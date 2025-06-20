package com.fimepay.merchantapp.chargebacks.dto;

import com.fimepay.merchantapp.chargebacks.model.ChargebackStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class ChargebackEventDTO {
    private UUID id;
    private UUID merchantId;
    private UUID originalTransactionId;
    private BigDecimal amount;
    private String reason;
    private ChargebackStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    // getters & setters...
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getOriginalTransactionId() {
        return originalTransactionId;
    }
    public void setOriginalTransactionId(UUID originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public ChargebackStatus getStatus() {
        return status;
    }
    public void setStatus(ChargebackStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }
    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }


      public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getMerchantId() {
        return merchantId;
    }
}
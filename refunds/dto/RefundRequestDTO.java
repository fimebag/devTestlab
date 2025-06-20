package com.fimepay.merchantapp.refunds.dto;

import com.fimepay.merchantapp.refunds.model.RefundStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RefundRequestDTO {
    private UUID id;
    private UUID transactionId;
    private BigDecimal amountRequested;
    private BigDecimal amountApproved;
    private BigDecimal reversalFee;
    private RefundStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private String reason;
    // getters & setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
    public BigDecimal getAmountRequested() {
        return amountRequested;
    }
    public void setAmountRequested(BigDecimal amountRequested) {
        this.amountRequested = amountRequested;
    }
    public BigDecimal getAmountApproved() {
        return amountApproved;
    }
    public void setAmountApproved(BigDecimal amountApproved) {
        this.amountApproved = amountApproved;
    }
    public BigDecimal getReversalFee() {
        return reversalFee;
    }
    public void setReversalFee(BigDecimal reversalFee) {
        this.reversalFee = reversalFee;
    }
    public RefundStatus getStatus() {
        return status;
    }
    public void setStatus(RefundStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
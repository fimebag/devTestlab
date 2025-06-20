package com.fimepay.merchantapp.settlement.dto;

import com.fimepay.merchantapp.settlement.model.TransferStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class InternalTransferDTO {
    private UUID id;
    private String fromWalletId;
    private String toWalletId;
    private BigDecimal amount;
    private String currency;
    private TransferStatus status;
    private LocalDateTime createdAt;
    private String approvedBy;
    // getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFromWalletId() {
        return fromWalletId;
    }
    public void setFromWalletId(String fromWalletId) {
        this.fromWalletId = fromWalletId;
    }
    public String getToWalletId() {
        return toWalletId;
    }
    public void setToWalletId(String toWalletId) {
        this.toWalletId = toWalletId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public TransferStatus getStatus() {
        return status;
    }
    public void setStatus(TransferStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}


package com.fimepay.merchantapp.settlement.dto;

import com.fimepay.merchantapp.settlement.model.SettlementSchedule;
import com.fimepay.merchantapp.settlement.model.SettlementStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SettlementDTO {
    private UUID id;
    private String walletId;
    private String bankAccountId;
    private BigDecimal amount;
    private String currency;
    private SettlementSchedule scheduleType;
    private SettlementStatus status;
    private LocalDateTime initiatedAt;
    private LocalDateTime settledAt;
    // getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getWalletId() {
        return walletId;
    }
    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }
    public String getBankAccountId() {
        return bankAccountId;
    }
    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
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
    public SettlementSchedule getScheduleType() {
        return scheduleType;
    }
    public void setScheduleType(SettlementSchedule scheduleType) {
        this.scheduleType = scheduleType;
    }
    public SettlementStatus getStatus() {
        return status;
    }
    public void setStatus(SettlementStatus status) {
        this.status = status;
    }
    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }
    public void setInitiatedAt(LocalDateTime initiatedAt) {
        this.initiatedAt = initiatedAt;
    }
    public LocalDateTime getSettledAt() {
        return settledAt;
    }
    public void setSettledAt(LocalDateTime settledAt) {
        this.settledAt = settledAt;
    }
}

package com.fimepay.merchantapp.settlement.dto;

import com.fimepay.merchantapp.settlement.model.SettlementSchedule;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateSettlementRequest {

    @NotBlank private String walletId;
    @NotBlank private String bankAccountId;
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    @NotBlank private String currency;
    @NotNull private SettlementSchedule scheduleType;
    // getters & setters…
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
}

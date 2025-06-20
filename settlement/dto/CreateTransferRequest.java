package com.fimepay.merchantapp.settlement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateTransferRequest {

    @NotBlank private String fromWalletId;
    @NotBlank private String toWalletId;
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    @NotBlank private String currency;
    // getters & setters…
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
}

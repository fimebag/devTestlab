package com.fimepay.merchantapp.settlement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateFxRequest {

    @NotBlank private String fromWalletId;
    @NotBlank private String toWalletId;
    @NotNull @DecimalMin("0.01") private BigDecimal amountIn;
    @NotNull @DecimalMin("0.000001") private BigDecimal rate;
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
    public BigDecimal getAmountIn() {
        return amountIn;
    }
    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }
    public BigDecimal getRate() {
        return rate;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}

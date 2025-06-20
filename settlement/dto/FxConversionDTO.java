package com.fimepay.merchantapp.settlement.dto;

import com.fimepay.merchantapp.settlement.model.ConversionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FxConversionDTO {
    private UUID id;
    private String fromWalletId;
    private String toWalletId;
    private BigDecimal amountIn;
    private BigDecimal amountOut;
    private BigDecimal rate;
    private ConversionStatus status;
    private LocalDateTime createdAt;
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
    public BigDecimal getAmountIn() {
        return amountIn;
    }
    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }
    public BigDecimal getAmountOut() {
        return amountOut;
    }
    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }
    public BigDecimal getRate() {
        return rate;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    public ConversionStatus getStatus() {
        return status;
    }
    public void setStatus(ConversionStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


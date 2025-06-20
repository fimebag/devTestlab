package com.fimepay.merchantapp.transactions.dto;

import com.fimepay.merchantapp.transactions.model.PaymentChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateFundingRequest {
    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String currency;

    @NotNull
    private PaymentChannel channel;

    // ── getters & setters ───────────────────────────────────────────────────

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public PaymentChannel getChannel() { return channel; }
    public void setChannel(PaymentChannel channel) { this.channel = channel; }
}

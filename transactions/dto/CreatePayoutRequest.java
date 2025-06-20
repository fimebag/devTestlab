package com.fimepay.merchantapp.transactions.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import com.fimepay.merchantapp.transactions.model.PaymentChannel;

public class CreatePayoutRequest {

    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    private String currency;

    @NotBlank
    private String channelReference; // e.g. beneficiary account

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentChannel getChannel() {
        return channel;
    }

    public void setChannel(PaymentChannel channel) {
        this.channel = channel;
    }

    private PaymentChannel channel;
    

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChannelReference() {
        return channelReference;
    }

    public void setChannelReference(String channelReference) {
        this.channelReference = channelReference;
    }

    // Optional: customerCountry, notes…

    // Getters & setters…
}


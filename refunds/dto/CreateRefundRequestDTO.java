

package com.fimepay.merchantapp.refunds.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateRefundRequestDTO {
    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;
    @NotBlank
    private String reason;
    // getters & setters
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
}
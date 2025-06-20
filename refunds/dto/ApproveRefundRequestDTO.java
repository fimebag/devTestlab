package com.fimepay.merchantapp.refunds.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ApproveRefundRequestDTO {
    @NotNull @DecimalMin("0.00")
    private BigDecimal approvedAmount;
    @DecimalMin("0.00")
    private BigDecimal reversalFee = BigDecimal.ZERO;
    // getters & setters
    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }
    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }
    public BigDecimal getReversalFee() {
        return reversalFee;
    }
    public void setReversalFee(BigDecimal reversalFee) {
        this.reversalFee = reversalFee;
    }
}
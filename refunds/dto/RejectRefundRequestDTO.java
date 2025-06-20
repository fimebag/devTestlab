package com.fimepay.merchantapp.refunds.dto;

import jakarta.validation.constraints.NotBlank;

public class RejectRefundRequestDTO {
    @NotBlank
    private String rejectionReason;
    // getters & setters

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}

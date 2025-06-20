package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.KycStatus;
import jakarta.validation.constraints.NotNull;

public class KycReviewRequest {
    @NotNull
    private KycStatus status;            // APPROVED or REJECTED
    private String rejectionReason;      // required if REJECTED
    public KycStatus getStatus() { return status; }
    public void setStatus(KycStatus status) { this.status = status; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}

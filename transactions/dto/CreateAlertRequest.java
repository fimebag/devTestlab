package com.fimepay.merchantapp.transactions.dto;

import com.fimepay.merchantapp.transactions.model.AlertChannel;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateAlertRequest {
    @NotNull private BigDecimal thresholdAmount;
    @NotBlank private String criteriaJson;
    @NotNull private AlertChannel channel;
    // Getters & setters…
    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }
    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }
    public String getCriteriaJson() {
        return criteriaJson;
    }
    public void setCriteriaJson(String criteriaJson) {
        this.criteriaJson = criteriaJson;
    }
    public AlertChannel getChannel() {
        return channel;
    }
    public void setChannel(AlertChannel channel) {
        this.channel = channel;
    }
}

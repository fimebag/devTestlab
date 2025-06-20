package com.fimepay.merchantapp.transactions.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateExportJobRequest {
    @NotBlank private String filterJson;
    @NotBlank private String scheduleCron;
    @NotBlank private String destination;
    // Getters & setters…
    public String getFilterJson() {
        return filterJson;
    }
    public void setFilterJson(String filterJson) {
        this.filterJson = filterJson;
    }
    public String getScheduleCron() {
        return scheduleCron;
    }
    public void setScheduleCron(String scheduleCron) {
        this.scheduleCron = scheduleCron;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
}

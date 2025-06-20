package com.fimepay.merchantapp.settlement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReconciliationReportDTO {
    private UUID id;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private String reportPath;
    private LocalDateTime generatedAt;
    // getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getPeriodStart() {
        return periodStart;
    }
    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }
    public LocalDate getPeriodEnd() {
        return periodEnd;
    }
    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }
    public String getReportPath() {
        return reportPath;
    }
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}

package com.fimepay.merchantapp.settlement.model;

import com.fimepay.merchantapp.model.Merchant;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reconciliation_reports")
public class ReconciliationReport {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional=false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @Column(nullable=false)
    private LocalDate periodStart;

    @Column(nullable=false)
    private LocalDate periodEnd;

    @Column(nullable=false, length=500)
    private String reportPath; // filesystem or S3 key

    @Column(nullable=false)
    private LocalDateTime generatedAt = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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

    // getters & setters...
}


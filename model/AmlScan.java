package com.fimepay.merchantapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="aml_scans")
public class AmlScan {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional=false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @Column(nullable=false)
    private String provider; // e.g. "MockAML"

    @Column(nullable=false)
    private String result;   // e.g. "CLEAR", "PEP_FOUND", "WATCHLIST"

    @Column(nullable=false)
    private LocalDateTime scannedAt = LocalDateTime.now();

    // Getters & Setters

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
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public LocalDateTime getScannedAt() {
        return scannedAt;
    }
    public void setScannedAt(LocalDateTime scannedAt) {
        this.scannedAt = scannedAt;
    }
}

package com.fimepay.merchantapp.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class AmlScanDTO {
    private UUID id;
    private String provider;
    private String result;
    private LocalDateTime scannedAt;
    // getters/setters...

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
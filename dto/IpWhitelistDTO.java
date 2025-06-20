package com.fimepay.merchantapp.dto;

import java.util.UUID;

public class IpWhitelistDTO {
    private UUID id;
    private String ipAddress;
    private boolean enabled;
    // getters/setters...

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}


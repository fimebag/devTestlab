package com.fimepay.merchantapp.dto;

public class CreateIpWhitelistRequest {
    private String ipAddress;
    private boolean enabled = true;
    // getters/setters...
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
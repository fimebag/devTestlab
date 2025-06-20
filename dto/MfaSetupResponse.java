package com.fimepay.merchantapp.dto;

public class MfaSetupResponse {
    private String secret;
    private String qrCodeUrl;
    // getters/setters
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }
}

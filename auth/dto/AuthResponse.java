package com.fimepay.merchantapp.auth.dto;


public class AuthResponse {
    private String accessToken;
    private String refreshToken;

    public AuthResponse() {}
    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken  = accessToken;
        this.refreshToken = refreshToken;
    }
    // getters & setters…
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
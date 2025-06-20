package com.fimepay.merchantapp.crypto;

import jakarta.validation.constraints.Size;

public class CryptoWalletUpdateRequest {
    @Size(max = 100)
    private String nickname;

    // getters & setters
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
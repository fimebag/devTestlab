package com.fimepay.merchantapp.bankaccount;

import jakarta.validation.constraints.Size;

public class BankAccountUpdateRequest {
    @Size(max = 100)
    private String nickname;

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
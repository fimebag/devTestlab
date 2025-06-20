// Login request
package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.*;

public class MerchantLoginRequest {
    @Email @NotBlank public String email;
    @NotBlank public String password;

    // Add these:
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
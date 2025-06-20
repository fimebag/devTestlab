// Email verification
package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.NotBlank;

public class EmailVerificationRequest {
    @NotBlank public String token;
}
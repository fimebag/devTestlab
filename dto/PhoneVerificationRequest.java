// Phone verification
package com.fimepay.merchantapp.dto;
import jakarta.validation.constraints.*;

public class PhoneVerificationRequest {
    @Email @NotBlank public String email;
    @NotBlank public String otp;
}
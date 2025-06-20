package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.WalletType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// Multi Currency

public class WalletDTO {
    private UUID id;
    private WalletType walletType;
    private String currency;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    // getters/setters
}
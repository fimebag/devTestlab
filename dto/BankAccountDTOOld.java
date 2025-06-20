// src/main/java/com/fimepay/merchantapp/dto/BankAccountDTO.java
package com.fimepay.merchantapp.dto;

import com.fimepay.merchantapp.model.BankAccountStatus;
import java.util.UUID;
//import java.math.BigDecimal;
import java.time.LocalDateTime;


public class BankAccountDTOOld {
    private UUID id;
    private String accountNumber;
    private String bankName;
    private String swiftCode;
    private BankAccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime microDepositSentAt;
    private LocalDateTime verifiedAt;
    // getters/setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public BankAccountStatus getStatus() {
        return status;
    }

    public void setStatus(BankAccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getMicroDepositSentAt() {
        return microDepositSentAt;
    }

    public void setMicroDepositSentAt(LocalDateTime microDepositSentAt) {
        this.microDepositSentAt = microDepositSentAt;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}

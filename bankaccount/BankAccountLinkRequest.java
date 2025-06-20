package com.fimepay.merchantapp.bankaccount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BankAccountLinkRequest {
    @NotBlank
    private String bankName;

    @NotBlank
    @Pattern(regexp = "\\d{8,17}")
    private String accountNumber;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String routingNumber;

    @NotBlank
    private String accountHolderName;

    // getters & setters
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getRoutingNumber() { return routingNumber; }
    public void setRoutingNumber(String routingNumber) { this.routingNumber = routingNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
}


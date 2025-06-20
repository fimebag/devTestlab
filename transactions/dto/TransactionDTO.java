package com.fimepay.merchantapp.transactions.dto;

import com.fimepay.merchantapp.transactions.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDTO {
    private UUID id;
    private TransactionType type;
    private PaymentChannel channel;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String customerCountry;
    private BigDecimal originalAmount;
    // three new fields from Old Transaction Table
    private String beneficiaryAccount;
    private String beneficiaryName;
    private String bankName;
    private BigDecimal convertedAmount;
    private BigDecimal fxRate;
    private String channelReference;
    private String notes;
    // Getters & setters…
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public PaymentChannel getChannel() {
        return channel;
    }
    public void setChannel(PaymentChannel channel) {
        this.channel = channel;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public TransactionStatus getStatus() {
        return status;
    }
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    public String getCustomerCountry() {
        return customerCountry;
    }
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }
    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }
    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }
    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }
    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
    public String getBeneficiaryName() {
        return beneficiaryName;
    }
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }
    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }
    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public BigDecimal getFxRate() {
        return fxRate;
    }
    public void setFxRate(BigDecimal fxRate) {
        this.fxRate = fxRate;
    }
    public String getChannelReference() {
        return channelReference;
    }
    public void setChannelReference(String channelReference) {
        this.channelReference = channelReference;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}

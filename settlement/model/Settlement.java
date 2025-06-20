package com.fimepay.merchantapp.settlement.model;


import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.Wallet;
import com.fimepay.merchantapp.bankaccount.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlements")
public class Settlement {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional=false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @ManyToOne(optional=false)
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    @ManyToOne(optional=false)
    @JoinColumn(name="bank_account_id")
    private BankAccount bankAccount;

    @Column(nullable=false, precision=19, scale=4)
    private BigDecimal amount;

    @Column(nullable=false, length=3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SettlementSchedule scheduleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SettlementStatus status = SettlementStatus.QUEUED;

    @Column(nullable=false)
    private LocalDateTime initiatedAt = LocalDateTime.now();

    private LocalDateTime settledAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
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

    public SettlementSchedule getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(SettlementSchedule scheduleType) {
        this.scheduleType = scheduleType;
    }

    public SettlementStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementStatus status) {
        this.status = status;
    }

    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public void setInitiatedAt(LocalDateTime initiatedAt) {
        this.initiatedAt = initiatedAt;
    }

    public LocalDateTime getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(LocalDateTime settledAt) {
        this.settledAt = settledAt;
    }

    // getters & setters...
}

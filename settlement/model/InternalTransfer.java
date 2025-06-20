package com.fimepay.merchantapp.settlement.model;

import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.Wallet;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "internal_transfers")
public class InternalTransfer {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @ManyToOne(optional = false)
    @JoinColumn(name="from_wallet_id")
    private Wallet fromWallet;

    @ManyToOne(optional = false)
    @JoinColumn(name="to_wallet_id")
    private Wallet toWallet;

    @Column(nullable=false, precision=19, scale=4)
    private BigDecimal amount;

    @Column(nullable=false, length=3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TransferStatus status = TransferStatus.PENDING;

    @Column(nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(length=100)
    private String approvedBy; // user email or id

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

    public Wallet getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(Wallet fromWallet) {
        this.fromWallet = fromWallet;
    }

    public Wallet getToWallet() {
        return toWallet;
    }

    public void setToWallet(Wallet toWallet) {
        this.toWallet = toWallet;
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

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    // getters & setters...
}


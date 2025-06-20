package com.fimepay.merchantapp.chargebacks.model;

import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.transactions.model.Transaction;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chargeback_events")
public class ChargebackEvent {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @ManyToOne(optional = false)
    @JoinColumn(name="original_transaction_id")
    private Transaction originalTransaction;

    @Column(nullable=false, precision=19, scale=4)
    private BigDecimal amount;

    @Column(columnDefinition="TEXT", nullable=false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ChargebackStatus status = ChargebackStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime resolvedAt;

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

    public Transaction getOriginalTransaction() {
        return originalTransaction;
    }

    public void setOriginalTransaction(Transaction originalTransaction) {
        this.originalTransaction = originalTransaction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ChargebackStatus getStatus() {
        return status;
    }

    public void setStatus(ChargebackStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    // getters & setters...
}

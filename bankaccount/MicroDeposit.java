package com.fimepay.merchantapp.bankaccount;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "micro_deposits")
public class MicroDeposit {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "bank_account_id", nullable = false)
    private UUID bankAccountId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant sentAt;

    @Column(nullable = false)
    private Boolean confirmed = false;

    @Column(nullable = true)
    private Instant confirmedAt;

    // getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getBankAccountId() { return bankAccountId; }
    public void setBankAccountId(UUID bankAccountId) { this.bankAccountId = bankAccountId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
    public Boolean getConfirmed() { return confirmed; }
    public void setConfirmed(Boolean confirmed) { this.confirmed = confirmed; }
    public Instant getConfirmedAt() { return confirmedAt; }
    public void setConfirmedAt(Instant confirmedAt) { this.confirmedAt = confirmedAt; }
}


package com.fimepay.merchantapp.refunds.model;

import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.transactions.model.Transaction;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refund_requests")
public class RefundRequest {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(nullable = false, precision=19, scale=4)
    private BigDecimal amountRequested;

    @Column(precision=19, scale=4)
    private BigDecimal amountApproved;

    @Column(precision=19, scale=4)
    private BigDecimal reversalFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status = RefundStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime processedAt;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reason;

    // Getters & setters...

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Merchant getMerchant() { return merchant; }
    public void setMerchant(Merchant merchant) { this.merchant = merchant; }

    public Transaction getTransaction() { return transaction; }
    public void setTransaction(Transaction transaction) { this.transaction = transaction; }

    public BigDecimal getAmountRequested() { return amountRequested; }
    public void setAmountRequested(BigDecimal amountRequested) { this.amountRequested = amountRequested; }

    public BigDecimal getAmountApproved() { return amountApproved; }
    public void setAmountApproved(BigDecimal amountApproved) { this.amountApproved = amountApproved; }

    public BigDecimal getReversalFee() { return reversalFee; }
    public void setReversalFee(BigDecimal reversalFee) { this.reversalFee = reversalFee; }

    public RefundStatus getStatus() { return status; }
    public void setStatus(RefundStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}

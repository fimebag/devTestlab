package com.fimepay.merchantapp.events;


import java.math.BigDecimal;
import java.util.UUID;

public class RefundApprovedEvent {
    private final UUID merchantId;
    private final UUID refundId;
    private final UUID transactionId;
    private final BigDecimal amountApproved;

    public RefundApprovedEvent(UUID merchantId,
                               UUID refundId,
                               UUID transactionId,
                               BigDecimal amountApproved) {
        this.merchantId      = merchantId;
        this.refundId        = refundId;
        this.transactionId   = transactionId;
        this.amountApproved  = amountApproved;
    }
    public UUID getMerchantId()     { return merchantId; }
    public UUID getRefundId()       { return refundId; }
    public UUID getTransactionId()  { return transactionId; }
    public BigDecimal getAmountApproved() { return amountApproved; }
}
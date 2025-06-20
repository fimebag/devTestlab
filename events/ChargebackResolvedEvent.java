package com.fimepay.merchantapp.events;

import java.math.BigDecimal;
import java.util.UUID;

public class ChargebackResolvedEvent {
    private final UUID merchantId;
    private final UUID chargebackId;
    private final UUID originalTransactionId;
    private final BigDecimal amount;

    public ChargebackResolvedEvent(UUID merchantId,
                                   UUID chargebackId,
                                   UUID originalTransactionId,
                                   BigDecimal amount) {
        this.merchantId            = merchantId;
        this.chargebackId          = chargebackId;
        this.originalTransactionId = originalTransactionId;
        this.amount                = amount;
    }
    public UUID getMerchantId()            { return merchantId; }
    public UUID getChargebackId()          { return chargebackId; }
    public UUID getOriginalTransactionId(){ return originalTransactionId; }
    public BigDecimal getAmount()          { return amount; }
}
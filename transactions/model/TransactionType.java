
package com.fimepay.merchantapp.transactions.model;

public enum TransactionType {
    INBOUND,        // money coming in (payment)
    OUTBOUND,       // money going out (payout)
    FX,             // currency conversion
    REFUND,         // merchant‐initiated refund
    CHARGEBACK      // chargeback/dispute reversal
}
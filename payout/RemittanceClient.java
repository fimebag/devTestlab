package com.fimepay.merchantapp.payout;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class RemittanceClient {
    /**
     * Stub for sending remittance transfers.
     */
    public String sendTransfer(String reference,
                               UUID merchantId,
                               BigDecimal amount,
                               String currency) {
        // TODO: call your remittance partner API
        return "REMIT_TXN_" + reference;
    }
}

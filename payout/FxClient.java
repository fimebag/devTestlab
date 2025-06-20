package com.fimepay.merchantapp.payout;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FxClient {
    /**
     * Stub for FX + direct bank deposit.
     */
    public String sendDeposit(String reference,
                              UUID merchantId,
                              BigDecimal amount,
                              String currency) {
        // TODO: perform FX conversion + bank API call
        return "FX_TXN_" + reference;
    }
}

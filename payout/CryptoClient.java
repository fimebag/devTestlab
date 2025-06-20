package com.fimepay.merchantapp.payout;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CryptoClient {
    /**
     * Stub for charging via Crypto on-ramp.
     */
    public String sendCharge(String reference,
                             UUID merchantId,
                             BigDecimal amount,
                             String currency) {
        // TODO: integrate with your crypto gateway
        return "CRYPTO_TXN_" + reference;
    }
}

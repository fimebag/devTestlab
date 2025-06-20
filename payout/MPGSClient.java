package com.fimepay.merchantapp.payout;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MPGSClient {
    /**
     * Stub for charging via MPGS (card).
     */
    public String sendCharge(String reference,
                             UUID merchantId,
                             BigDecimal amount,
                             String currency) {
        // TODO: call real MPGS API
        return "MPGS_TXN_" + reference;
    }
}

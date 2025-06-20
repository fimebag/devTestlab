package com.fimepay.merchantapp.payout;

import java.math.BigDecimal;
import java.util.UUID;

import com.fimepay.merchantapp.transactions.model.PaymentChannel;

public interface PaymentGatewayService {

    /**
     * Send a payout to the external gateway.
     * @param reference your internal transaction reference
     * @param merchantId the merchant
     * @param amount payout amount
     * @param currency currency code (e.g. "NGN")
     * @return the external transaction ID (or throws on error)
     */
    String sendPayout(String reference,
                      UUID merchantId,
                      BigDecimal amount,
                      String currency);

    /**
     * Issue a refund via the gateway.
     * @param originalReference the original payout reference
     * @param refundReference your new refund reference
     * @param amount refund amount
     * @return the external refund ID
     */
    String sendRefund(String originalReference,
                      String refundReference,
                      BigDecimal amount);

    /**
     * Issue a chargeback via the gateway.
     * @param originalReference the original payout reference
     * @param chargebackReference your new chargeback reference
     * @param amount chargeback amount
     * @return the external chargeback ID
     */
    String sendChargeback(String originalReference,
                          String chargebackReference,
                          BigDecimal amount);

    String charge(String reference,
                  UUID merchantId,
                  BigDecimal amount,
                  String currency,
                  PaymentChannel channel);
}

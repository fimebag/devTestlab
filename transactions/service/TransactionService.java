package com.fimepay.merchantapp.transactions.service;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {
    Page<TransactionDTO> listTransactions(
        UUID merchantId,
        TransactionType type,
        TransactionStatus status,
        PaymentChannel channel,
        Pageable pageable
    );
    TransactionDTO getDetails(UUID id);
    TransactionDTO initiatePayout(UUID merchantId, CreatePayoutRequest req);
        /**
     * Perform a refund payout: create a refund transaction and debit the wallet.
     */
    void initiateOutboundRefund(
        UUID merchantId,
        UUID originalTransactionId,
        BigDecimal amount,
        UUID refundRequestId
    );
        /**
     * Perform a chargeback payout: create a chargeback transaction and debit the wallet.
     */
    void initiateChargebackPayout(
        UUID merchantId,
        UUID originalTransactionId,
        BigDecimal amount,
        UUID chargebackEventId
    );
}

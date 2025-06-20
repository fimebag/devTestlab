package com.fimepay.merchantapp.events;

// src/main/java/com/fimepay/merchantapp/events/TransactionPipelineListener.java

import com.fimepay.merchantapp.service.*;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import com.fimepay.merchantapp.transactions.service.*;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class TransactionPipelineListener {

    private final TransactionService transactionService;
    private final AuditService auditService;
    private final NotificationService notificationService;

    public TransactionPipelineListener(TransactionService transactionService,
                                       AuditService auditService,
                                       NotificationService notificationService) {
        this.transactionService  = transactionService;
        this.auditService        = auditService;
        this.notificationService = notificationService;
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onRefundApproved(RefundApprovedEvent evt) {
        // 1) Money movement
        transactionService.initiateOutboundRefund(
            evt.getMerchantId(),
            evt.getTransactionId(),
            evt.getAmountApproved(),
            evt.getRefundId()
        );

        // 2) Audit log
        String user = getCurrentUsername();
        auditService.logActivity(
            user,
            "REFUND_APPROVED",
            "Refund " + evt.getRefundId() + " approved for transaction " + evt.getTransactionId()
        );

        // 3) Notification
        notificationService.notifyRefundApproved(
            evt.getMerchantId(),
            evt.getRefundId(),
            evt.getAmountApproved()
        );
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onChargebackResolved(ChargebackResolvedEvent evt) {
        // 1) Money movement
        transactionService.initiateChargebackPayout(
            evt.getMerchantId(),
            evt.getOriginalTransactionId(),
            evt.getAmount(),
            evt.getChargebackId()
        );

        // 2) Audit log
        String user = getCurrentUsername();
        auditService.logActivity(
            user,
            "CHARGEBACK_RESOLVED",
            "Chargeback " + evt.getChargebackId() + " resolved for transaction " + evt.getOriginalTransactionId()
        );

        // 3) Notification
        notificationService.notifyChargebackResolved(
            evt.getMerchantId(),
            evt.getChargebackId(),
            evt.getAmount()
        );
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null ? auth.getName() : "SYSTEM");
    }
}

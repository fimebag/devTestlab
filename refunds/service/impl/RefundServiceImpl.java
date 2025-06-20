package com.fimepay.merchantapp.refunds.service.impl;

import com.fimepay.merchantapp.events.RefundApprovedEvent;
//import com.fimepay.merchantapp.events.RefundRejectedEvent; // optional
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.refunds.dto.*;
import com.fimepay.merchantapp.refunds.model.*;
import com.fimepay.merchantapp.refunds.repository.RefundRequestRepository;
import com.fimepay.merchantapp.repository.MerchantRepository;
import com.fimepay.merchantapp.transactions.model.Transaction;
import com.fimepay.merchantapp.transactions.repository.TransactionRepository;
import com.fimepay.merchantapp.refunds.service.RefundService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class RefundServiceImpl implements RefundService {

    private final RefundRequestRepository refundRepo;
    private final MerchantRepository      merchantRepo;
    private final TransactionRepository   txRepo;
    private final ModelMapper             mapper;
    private final ApplicationEventPublisher events;

    public RefundServiceImpl(RefundRequestRepository refundRepo,
                             MerchantRepository merchantRepo,
                             TransactionRepository txRepo,
                             ModelMapper mapper,
                             ApplicationEventPublisher events) {
        this.refundRepo    = refundRepo;
        this.merchantRepo  = merchantRepo;
        this.txRepo        = txRepo;
        this.mapper        = mapper;
        this.events        = events;
    }

    @Override
    public RefundRequestDTO requestRefund(UUID merchantId,
                                          UUID transactionId,
                                          CreateRefundRequestDTO req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        Transaction tx = txRepo.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));

        RefundRequest r = new RefundRequest();
        r.setMerchant(m);
        r.setTransaction(tx);
        r.setAmountRequested(req.getAmount());
        r.setReason(req.getReason());

        RefundRequest saved = refundRepo.save(r);
        return mapper.map(saved, RefundRequestDTO.class);
    }

    @Override
    public RefundRequestDTO approveRefund(UUID merchantId,
                                          UUID refundId,
                                          ApproveRefundRequestDTO req) {
        RefundRequest r = refundRepo.findById(refundId)
            .orElseThrow(() -> new RuntimeException("Refund request not found"));

        r.setAmountApproved(req.getApprovedAmount());
        r.setReversalFee(req.getReversalFee());
        r.setStatus(RefundStatus.APPROVED);
        r.setProcessedAt(LocalDateTime.now());

        RefundRequest saved = refundRepo.save(r);
        
        // ── Publish the “refund approved” event ──
        events.publishEvent(new RefundApprovedEvent(
            merchantId,
            saved.getId(),
            saved.getTransaction().getId(),
            saved.getAmountApproved()
        ));                                 

        return mapper.map(saved, RefundRequestDTO.class);
    }

    @Override
    public RefundRequestDTO rejectRefund(UUID merchantId,
                                         UUID refundId,
                                         RejectRefundRequestDTO req) {
        RefundRequest r = refundRepo.findById(refundId)
            .orElseThrow(() -> new RuntimeException("Refund request not found"));

        r.setStatus(RefundStatus.REJECTED);
        r.setProcessedAt(LocalDateTime.now());
        r.setReason(req.getRejectionReason());

        RefundRequest saved = refundRepo.save(r);

        /*    //── (Optional) publish a “refund rejected” event ──
        events.publishEvent(new RefundRejectedEvent(
            merchantId,
            saved.getId(),
            saved.getTransaction().getId()
        )); */

        return mapper.map(saved, RefundRequestDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefundRequestDTO> listRefunds(UUID merchantId, Pageable pageable) {
        return refundRepo.findByMerchant_Id(merchantId, pageable)
                         .map(r -> mapper.map(r, RefundRequestDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public RefundRequestDTO getRefund(UUID merchantId, UUID refundId) {
        RefundRequest r = refundRepo.findById(refundId)
            .orElseThrow(() -> new RuntimeException("Refund request not found"));
        return mapper.map(r, RefundRequestDTO.class);
    }
}

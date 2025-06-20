package com.fimepay.merchantapp.chargebacks.service.impl;

import com.fimepay.merchantapp.chargebacks.dto.*;
import com.fimepay.merchantapp.chargebacks.model.*;
import com.fimepay.merchantapp.chargebacks.repository.ChargebackEventRepository;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.repository.MerchantRepository;
import com.fimepay.merchantapp.transactions.model.Transaction;
import com.fimepay.merchantapp.transactions.repository.TransactionRepository;
import com.fimepay.merchantapp.chargebacks.service.ChargebackService;
import com.fimepay.merchantapp.events.ChargebackResolvedEvent;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ChargebackServiceImpl implements ChargebackService {

    private final ChargebackEventRepository cbRepo;
    private final MerchantRepository merchantRepo;
    private final TransactionRepository txRepo;
    private final ModelMapper mapper;
    private final ApplicationEventPublisher events;


    @Autowired
    public ChargebackServiceImpl(
        ChargebackEventRepository cbRepo,
        MerchantRepository merchantRepo,
        TransactionRepository txRepo,
        ModelMapper mapper,
        ApplicationEventPublisher events
    ) {
        this.cbRepo        = cbRepo;
        this.merchantRepo  = merchantRepo;
        this.txRepo        = txRepo;
        this.mapper        = mapper;
        this.events        = events;
    }

    @Override
    public ChargebackEventDTO createChargeback(UUID merchantId,
                                                UUID transactionId,
                                                CreateChargebackRequestDTO req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        Transaction tx = txRepo.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));

        ChargebackEvent cb = new ChargebackEvent();
        cb.setMerchant(m);
        cb.setOriginalTransaction(tx);
        cb.setAmount(req.getAmount());
        cb.setReason(req.getReason());
        ChargebackEvent saved = cbRepo.save(cb);
        return mapper.map(saved, ChargebackEventDTO.class);
    }

    @Override
    public ChargebackEventDTO disputeChargeback(UUID merchantId, UUID chargebackId) {
        ChargebackEvent cb = cbRepo.findById(chargebackId)
            .orElseThrow(() -> new RuntimeException("Chargeback not found"));
        cb.setStatus(ChargebackStatus.DISPUTED);
        return mapper.map(cbRepo.save(cb), ChargebackEventDTO.class);
    }

    @Override
    public ChargebackEventDTO resolveChargeback(UUID merchantId, UUID cbId) {
        ChargebackEvent cb = cbRepo.findById(cbId)
            .orElseThrow(() -> new RuntimeException("Chargeback not found"));
        cb.setStatus(ChargebackStatus.RESOLVED);
        cb.setResolvedAt(LocalDateTime.now());
        ChargebackEvent saved = cbRepo.save(cb);

        // ← publish after resolution
        events.publishEvent(new ChargebackResolvedEvent(
            merchantId,
            saved.getId(),
            saved.getOriginalTransaction().getId(),
            saved.getAmount()
        ));

        return mapper.map(saved, ChargebackEventDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChargebackEventDTO> listChargebacks(UUID merchantId, Pageable pageable) {
        return cbRepo.findByMerchant_Id(merchantId, pageable)
            .map(cb -> mapper.map(cb, ChargebackEventDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public ChargebackEventDTO getChargeback(UUID merchantId, UUID chargebackId) {
        ChargebackEvent cb = cbRepo.findById(chargebackId)
            .orElseThrow(() -> new RuntimeException("Chargeback not found"));
        return mapper.map(cb, ChargebackEventDTO.class);
    }
}

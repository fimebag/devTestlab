package com.fimepay.merchantapp.transactions.service.impl;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.model.*;
import com.fimepay.merchantapp.transactions.repository.TransactionAlertRepository;
import com.fimepay.merchantapp.transactions.service.AlertService;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.repository.MerchantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

    private final TransactionAlertRepository alertRepo;
    private final MerchantRepository merchantRepo;
    private final ModelMapper mapper;

    public AlertServiceImpl(
            TransactionAlertRepository alertRepo,
            MerchantRepository merchantRepo,
            ModelMapper mapper) {
        this.alertRepo = alertRepo;
        this.merchantRepo = merchantRepo;
        this.mapper = mapper;
    }

    @Override
    public void evaluateAlerts(Transaction tx) {
        // stub: no-op
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionAlertDTO> listAlerts(UUID merchantId) {
        return alertRepo.findByMerchant_Id(merchantId)
                .stream()
                .map(a -> mapper.map(a, TransactionAlertDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionAlertDTO createAlert(UUID merchantId, CreateAlertRequest req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        TransactionAlert alert = new TransactionAlert();
        alert.setMerchant(m);
        alert.setThresholdAmount(req.getThresholdAmount());
        alert.setCriteriaJson(req.getCriteriaJson());
        alert.setChannel(req.getChannel());
        TransactionAlert saved = alertRepo.save(alert);
        return mapper.map(saved, TransactionAlertDTO.class);
    }
}

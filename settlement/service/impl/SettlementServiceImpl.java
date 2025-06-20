package com.fimepay.merchantapp.settlement.service.impl;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.model.*;
import com.fimepay.merchantapp.settlement.repository.SettlementRepository;
import com.fimepay.merchantapp.model.*;
import com.fimepay.merchantapp.repository.*;
import com.fimepay.merchantapp.settlement.service.SettlementService;
import com.fimepay.merchantapp.bankaccount.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository repo;
    private final MerchantRepository merchantRepo;
    private final WalletRepository walletRepo;
    private final BankAccountRepository bankRepo;
    private final ModelMapper mapper;

    @Autowired
    public SettlementServiceImpl(SettlementRepository repo,
                                 MerchantRepository merchantRepo,
                                 WalletRepository walletRepo,
                                 BankAccountRepository bankRepo,
                                 ModelMapper mapper) {
        this.repo = repo;
        this.merchantRepo = merchantRepo;
        this.walletRepo = walletRepo;
        this.bankRepo = bankRepo;
        this.mapper = mapper;
    }

    @Override
    public SettlementDTO scheduleSettlement(UUID merchantId, CreateSettlementRequest req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        Wallet w = walletRepo.findById(UUID.fromString(req.getWalletId()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));
        BankAccount b = bankRepo.findById(UUID.fromString(req.getBankAccountId()))
            .orElseThrow(() -> new RuntimeException("Bank account not found"));

        Settlement st = new Settlement();
        st.setMerchant(m);
        st.setWallet(w);
        st.setBankAccount(b);
        st.setAmount(req.getAmount());
        st.setCurrency(req.getCurrency());
        st.setScheduleType(req.getScheduleType());
        st.setStatus(SettlementStatus.QUEUED);
        st.setInitiatedAt(LocalDateTime.now());

        Settlement saved = repo.save(st);
        return mapper.map(saved, SettlementDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SettlementDTO> listSettlements(UUID merchantId, Pageable pageable) {
        return repo.findByMerchant_Id(merchantId, pageable)
                   .map(s -> mapper.map(s, SettlementDTO.class));
    }

    @Override
    public SettlementDTO runSettlementNow(UUID merchantId, UUID settlementId) {
        Settlement st = repo.findById(settlementId)
            .orElseThrow(() -> new RuntimeException("Settlement not found"));
        st.setStatus(SettlementStatus.SETTLED);
        st.setSettledAt(LocalDateTime.now());
        Settlement saved = repo.save(st);
        return mapper.map(saved, SettlementDTO.class);
    }
}

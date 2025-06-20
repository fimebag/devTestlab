package com.fimepay.merchantapp.settlement.service.impl;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.model.*;
import com.fimepay.merchantapp.settlement.repository.InternalTransferRepository;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.Wallet;
import com.fimepay.merchantapp.repository.MerchantRepository;
import com.fimepay.merchantapp.repository.WalletRepository;
import com.fimepay.merchantapp.settlement.service.TransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private final InternalTransferRepository repo;
    private final MerchantRepository merchantRepo;
    private final WalletRepository walletRepo;
    private final ModelMapper mapper;

    @Autowired
    public TransferServiceImpl(InternalTransferRepository repo,
                               MerchantRepository merchantRepo,
                               WalletRepository walletRepo,
                               ModelMapper mapper) {
        this.repo = repo;
        this.merchantRepo = merchantRepo;
        this.walletRepo = walletRepo;
        this.mapper = mapper;
    }

    @Override
    public InternalTransferDTO createTransfer(UUID merchantId, CreateTransferRequest req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        Wallet from = walletRepo.findById(UUID.fromString(req.getFromWalletId()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));
        Wallet to = walletRepo.findById(UUID.fromString(req.getToWalletId()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));

        InternalTransfer t = new InternalTransfer();
        t.setMerchant(m);
        t.setFromWallet(from);
        t.setToWallet(to);
        t.setAmount(req.getAmount());
        t.setCurrency(req.getCurrency());
        InternalTransfer saved = repo.save(t);
        return mapper.map(saved, InternalTransferDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InternalTransferDTO> listTransfers(UUID merchantId, Pageable pageable) {
        return repo.findByMerchant_Id(merchantId, pageable)
                   .map(t -> mapper.map(t, InternalTransferDTO.class));
    }

    @Override
    public InternalTransferDTO approveTransfer(UUID merchantId, UUID transferId) {
        InternalTransfer t = repo.findById(transferId)
            .orElseThrow(() -> new RuntimeException("Transfer not found"));
        t.setStatus(TransferStatus.APPROVED);
        t.setApprovedBy("system"); // or current user
        InternalTransfer saved = repo.save(t);
        return mapper.map(saved, InternalTransferDTO.class);
    }
}


// src/main/java/com/fimepay/merchantapp/crypto/CryptoWalletServiceImpl.java
package com.fimepay.merchantapp.crypto;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import com.fimepay.merchantapp.exception.ResourceNotFoundException;
//import com.fimepay.merchantapp.crypto.CryptoWallet;



import java.util.List;
import java.util.UUID;
import java.time.Instant;
import java.util.stream.Collectors;


@Service
public class CryptoWalletServiceImpl implements CryptoWalletService {

    private final CryptoWalletRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public CryptoWalletServiceImpl(CryptoWalletRepository repo, ModelMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CryptoWalletDTO> list(UUID merchantId) {
        return repo.findByMerchantId(merchantId).stream()
            .map(w -> mapper.map(w, CryptoWalletDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CryptoWalletDTO get(UUID merchantId, UUID walletId) {
        CryptoWallet w = repo.findById(walletId)
            .filter(x -> x.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Crypto wallet not found"));
        return mapper.map(w, CryptoWalletDTO.class);
    }

    @Override
    @Transactional
    public CryptoWalletDTO create(UUID merchantId, CryptoWalletLinkRequest req) {
        CryptoWallet w = new CryptoWallet();
        w.setMerchantId(merchantId);
        w.setCurrency(req.getCurrency());
        w.setNetwork(req.getNetwork());
        w.setAddress(req.getAddress());
        w.setStatus(com.fimepay.merchantapp.bankaccount.VerificationStatus.PENDING_VERIFICATION);
        w.setLinkedAt(Instant.now());
        w = repo.save(w);
        return mapper.map(w, CryptoWalletDTO.class);
    }

    @Override
    @Transactional
    public CryptoWalletDTO update(UUID merchantId, UUID walletId, CryptoWalletUpdateRequest req) {
        CryptoWallet w = repo.findById(walletId)
            .filter(x -> x.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Crypto wallet not found"));
        w.setNickname(req.getNickname());
        return mapper.map(repo.save(w), CryptoWalletDTO.class);
    }

    @Override
    @Transactional
    public void delete(UUID merchantId, UUID walletId) {
        CryptoWallet w = repo.findById(walletId)
            .filter(x -> x.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Crypto wallet not found"));
        if (w.getStatus() == com.fimepay.merchantapp.bankaccount.VerificationStatus.PENDING_VERIFICATION) {
            throw new IllegalStateException("Cannot delete wallet pending verification");
        }
        repo.delete(w);
    }
}

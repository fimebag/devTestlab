package com.fimepay.merchantapp.auth.service.impl;

import com.fimepay.merchantapp.auth.model.RefreshToken;
import com.fimepay.merchantapp.auth.repository.RefreshTokenRepository;
import com.fimepay.merchantapp.auth.service.RefreshTokenService;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;
import java.util.List;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh.expiration:86400000}")            // e.g. 7 days in seconds
    private long refreshTokenDurationSec;

    private final RefreshTokenRepository repo;
    private final MerchantRepository merchantRepo;

    public RefreshTokenServiceImpl(RefreshTokenRepository repo,
                                   MerchantRepository merchantRepo) {
        this.repo = repo;
        this.merchantRepo = merchantRepo;
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(UUID merchantId) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        RefreshToken t = new RefreshToken();
        t.setMerchant(m);
        t.setToken(UUID.randomUUID().toString());
        t.setIssuedAt(Instant.now());
        t.setExpiresAt(Instant.now().plusSeconds(refreshTokenDurationSec));
        return repo.save(t);
    }

    @Override
public RefreshToken verifyRefreshToken(String token) {
    RefreshToken t = repo.findByToken(token)
        .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    if (t.isRevoked() || t.getExpiresAt().isBefore(Instant.now())) {
        throw new RuntimeException("Refresh token expired or revoked");
    }
    return t;
}

    @Override
    @Transactional
    public void revokeRefreshTokens(UUID merchantId) {
        List<RefreshToken> tokens = repo.findAllByMerchant_Id(merchantId);
        tokens.forEach(t -> t.setRevoked(true));
        repo.saveAll(tokens);
    }

}
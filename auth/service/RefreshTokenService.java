package com.fimepay.merchantapp.auth.service;


import com.fimepay.merchantapp.auth.model.RefreshToken;
import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(UUID merchantId);
    RefreshToken verifyRefreshToken(String token);
    void revokeRefreshTokens(UUID merchantId);
}
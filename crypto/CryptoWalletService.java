// src/main/java/com/fimepay/merchantapp/crypto/CryptoWalletService.java
package com.fimepay.merchantapp.crypto;

import java.util.List;
import java.util.UUID;

public interface CryptoWalletService {
    List<CryptoWalletDTO> list(UUID merchantId);
    CryptoWalletDTO get(UUID merchantId, UUID walletId);
    CryptoWalletDTO create(UUID merchantId, CryptoWalletLinkRequest req);
    CryptoWalletDTO update(UUID merchantId, UUID walletId, CryptoWalletUpdateRequest req);
    void delete(UUID merchantId, UUID walletId);
}

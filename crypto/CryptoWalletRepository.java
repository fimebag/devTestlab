package com.fimepay.merchantapp.crypto;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CryptoWalletRepository 
    extends JpaRepository<CryptoWallet, UUID> {

    List<CryptoWallet> findByMerchantId(UUID merchantId);

}
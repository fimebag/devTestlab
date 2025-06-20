package com.fimepay.merchantapp.repository;


import com.fimepay.merchantapp.model.Wallet;
import com.fimepay.merchantapp.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByMerchant_IdAndWalletTypeAndCurrency(UUID merchantId, WalletType type, String currency);
    List<Wallet> findByMerchant_Id(UUID merchantId);
}
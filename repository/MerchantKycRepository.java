package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.MerchantKyc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantKycRepository extends JpaRepository<MerchantKyc, UUID> {
    Optional<MerchantKyc> findByMerchantId(UUID merchantId);
}

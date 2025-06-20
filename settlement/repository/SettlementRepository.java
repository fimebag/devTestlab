package com.fimepay.merchantapp.settlement.repository;

import com.fimepay.merchantapp.settlement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface SettlementRepository extends JpaRepository<Settlement, UUID> {
    Page<Settlement> findByMerchant_Id(UUID merchantId, Pageable pageable);
    Optional<Settlement> findByMerchantIdAndId(UUID merchantId, UUID id);

}
package com.fimepay.merchantapp.chargebacks.repository;

import com.fimepay.merchantapp.chargebacks.model.ChargebackEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ChargebackEventRepository extends JpaRepository<ChargebackEvent, UUID> {
    Page<ChargebackEvent> findByMerchant_Id(UUID merchantId, Pageable pageable);
}

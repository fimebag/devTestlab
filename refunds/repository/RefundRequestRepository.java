package com.fimepay.merchantapp.refunds.repository;

import com.fimepay.merchantapp.refunds.model.RefundRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface RefundRequestRepository extends JpaRepository<RefundRequest, UUID> {
    Page<RefundRequest> findByMerchant_Id(UUID merchantId, Pageable pageable);
}
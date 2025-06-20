package com.fimepay.merchantapp.settlement.repository;

import com.fimepay.merchantapp.settlement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface InternalTransferRepository extends JpaRepository<InternalTransfer, UUID> {
    Page<InternalTransfer> findByMerchant_Id(UUID merchantId, Pageable pageable);
}

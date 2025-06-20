package com.fimepay.merchantapp.repository;


import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmlScanRepository extends JpaRepository<AmlScan, UUID> {
    List<AmlScan> findByMerchant_Id(UUID merchantId);
}
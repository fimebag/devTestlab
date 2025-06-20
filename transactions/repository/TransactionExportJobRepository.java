package com.fimepay.merchantapp.transactions.repository;

import com.fimepay.merchantapp.transactions.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;



public interface TransactionExportJobRepository extends JpaRepository<TransactionExportJob, UUID> {
    List<TransactionExportJob> findByMerchant_Id(UUID merchantId);
}


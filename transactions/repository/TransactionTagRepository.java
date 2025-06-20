package com.fimepay.merchantapp.transactions.repository;

import com.fimepay.merchantapp.transactions.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;



public interface TransactionTagRepository extends JpaRepository<TransactionTag, UUID> {
    List<TransactionTag> findByTransaction_Id(UUID transactionId);
}
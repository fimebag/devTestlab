package com.fimepay.merchantapp.transactions.repository;

import com.fimepay.merchantapp.transactions.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findByMerchant_Id(UUID merchantId, Pageable pageable);
    Page<Transaction> findByMerchant_IdAndType(UUID merchantId, TransactionType type, Pageable pageable);
    Page<Transaction> findByMerchant_IdAndStatus(UUID merchantId, TransactionStatus status, Pageable pageable);
    Page<Transaction> findByMerchant_IdAndTypeAndStatus(UUID merchantId,
                                                       TransactionType type,
                                                       TransactionStatus status,
                                                       Pageable pageable);
    List<Transaction> findAllByMerchant_Id(UUID merchantId);
    Optional<Transaction> findByReference(String reference);


}
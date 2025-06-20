// src/main/java/com/fimepay/merchantapp/repository/KycDocumentRepository.java
package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.KycDocument;
import com.fimepay.merchantapp.model.DocumentType;
import com.fimepay.merchantapp.model.KycStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KycDocumentRepository extends JpaRepository<KycDocument, UUID> {
    List<KycDocument> findByMerchant_Id(UUID merchantId);
    Optional<KycDocument> findByMerchant_IdAndDocumentType(UUID merchantId, DocumentType type);
    List<KycDocument> findByStatus(KycStatus status);
}


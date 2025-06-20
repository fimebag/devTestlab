package com.fimepay.merchantapp.idempotency.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fimepay.merchantapp.idempotency.model.IdempotencyKey;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// src/main/java/com/fimepay/merchantapp/idempotency/repository/IdempotencyKeyRepository.java
public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, UUID> {
    Optional<IdempotencyKey> findByMerchantIdAndKeyAndMethodAndPath(
        UUID merchantId, String key, String method, String path);

         // purge all records older than ...
    int deleteAllByExpiresAtBefore(LocalDateTime cutoff);
  }
  
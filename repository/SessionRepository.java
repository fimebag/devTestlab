package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findByMerchant_Id(UUID merchantId);
    Optional<Session> findBySessionId(String sessionId);
}
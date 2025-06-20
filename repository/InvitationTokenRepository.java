package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface InvitationTokenRepository extends JpaRepository<InvitationToken, UUID> {

    /*@Query("SELECT t FROM InvitationToken t WHERE t.token = :token AND t.expiresAt > :now AND t.status = 'PENDING'")
    Optional<InvitationToken> findValidToken(String token, LocalDateTime now);*/

    boolean existsByEmailAndMerchantIdAndStatus(String email, UUID merchantId, InvitationStatus status);
    Optional<InvitationToken> findByToken(String token);
    List<InvitationToken> findByMerchant_IdAndStatus(UUID merchantId, InvitationStatus status);
}
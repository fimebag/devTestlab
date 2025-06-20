package com.fimepay.merchantapp.auth.repository;


import com.fimepay.merchantapp.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByMerchant_Id(UUID merchantId);
    // or if you really want to delete: void deleteAllByMerchant_Id(UUID merchantId);
}
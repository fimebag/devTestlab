package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MerchantUserRepository extends JpaRepository<MerchantUser, UUID> {
    List<MerchantUser> findByMerchant_Id(UUID merchantId);
    Optional<MerchantUser> findByMerchant_IdAndEmail(UUID merchantId, String email);
}


package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface MfaConfigRepository extends JpaRepository<MfaConfig, UUID> {
    Optional<MfaConfig> findByMerchant_Id(UUID merchantId);
}    


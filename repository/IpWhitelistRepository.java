package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IpWhitelistRepository extends JpaRepository<IpWhitelist, UUID> {
    List<IpWhitelist> findByMerchant_Id(UUID merchantId);
}
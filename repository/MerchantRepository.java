package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    Optional<Merchant> findByEmail(String email);
}

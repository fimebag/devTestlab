package com.fimepay.merchantapp.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MicroDepositRepository extends JpaRepository<MicroDeposit, UUID> {
    List<MicroDeposit> findByBankAccountId(UUID bankAccountId);
    void deleteByBankAccountId(UUID bankAccountId);
}


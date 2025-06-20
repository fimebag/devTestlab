package com.fimepay.merchantapp.bankaccount;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {
    List<BankAccountDTO> list(UUID merchantId);
    BankAccountDTO get(UUID merchantId, UUID accountId);
    BankAccountDTO create(UUID merchantId, BankAccountLinkRequest req);
    BankAccountDTO update(UUID merchantId, UUID accountId, BankAccountUpdateRequest req);
    void delete(UUID merchantId, UUID accountId);
    void resendMicroDeposits(UUID merchantId, UUID accountId);
    MicroDepositStatusDTO getMicroDepositStatus(UUID merchantId, UUID accountId);
    void verifyMicroDeposits(UUID merchantId, UUID accountId, BankAccountVerifyRequest req);
}

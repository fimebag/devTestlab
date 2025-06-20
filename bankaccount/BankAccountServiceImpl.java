package com.fimepay.merchantapp.bankaccount;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import com.fimepay.merchantapp.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.Instant;
import java.math.BigDecimal;
import java.util.Random;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repo;
    private final MicroDepositRepository microRepo;
    private final ModelMapper mapper;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository repo,
                                  MicroDepositRepository microRepo,
                                  ModelMapper mapper) {
        this.repo      = repo;
        this.microRepo = microRepo;
        this.mapper    = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountDTO> list(UUID merchantId) {
        return repo.findByMerchantId(merchantId).stream()
            .map(acc -> mapper.map(acc, BankAccountDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccountDTO get(UUID merchantId, UUID accountId) {
        BankAccount acc = repo.findById(accountId)
            .filter(a -> a.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        return mapper.map(acc, BankAccountDTO.class);
    }

    @Override
    @Transactional
    public BankAccountDTO create(UUID merchantId, BankAccountLinkRequest req) {
        BankAccount acc = new BankAccount();
        acc.setMerchantId(merchantId);
        acc.setBankName(req.getBankName());
        acc.setAccountNumber(req.getAccountNumber());
        acc.setRoutingNumber(req.getRoutingNumber());
        acc.setAccountHolderName(req.getAccountHolderName());
        acc.setStatus(VerificationStatus.PENDING_VERIFICATION);
        acc.setLinkedAt(Instant.now());
        acc = repo.save(acc);

        // trigger micro-deposits
        resendMicroDeposits(merchantId, acc.getId());
        return mapper.map(acc, BankAccountDTO.class);
    }

    @Override
    @Transactional
    public BankAccountDTO update(UUID merchantId, UUID accountId, BankAccountUpdateRequest req) {
        BankAccount acc = repo.findById(accountId)
            .filter(a -> a.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        acc.setNickname(req.getNickname());
        return mapper.map(repo.save(acc), BankAccountDTO.class);
    }

    @Override
    @Transactional
    public void delete(UUID merchantId, UUID accountId) {
        BankAccount acc = repo.findById(accountId)
            .filter(a -> a.getMerchantId().equals(merchantId))
            .orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        if (acc.getStatus() == VerificationStatus.PENDING_VERIFICATION) {
            throw new IllegalStateException("Cannot delete account pending verification");
        }
        microRepo.deleteByBankAccountId(accountId);
        repo.delete(acc);
    }

    @Override
    @Transactional
    public void resendMicroDeposits(UUID merchantId, UUID accountId) {
        microRepo.deleteByBankAccountId(accountId);
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            MicroDeposit md = new MicroDeposit();
            md.setBankAccountId(accountId);
            md.setAmount(BigDecimal.valueOf(rand.nextInt(90) + 10)
                           .divide(BigDecimal.valueOf(100)));
            md.setSentAt(Instant.now());
            microRepo.save(md);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MicroDepositStatusDTO getMicroDepositStatus(UUID merchantId, UUID accountId) {
        List<MicroDeposit> list = microRepo.findByBankAccountId(accountId);
        MicroDepositStatusDTO dto = new MicroDepositStatusDTO();
        dto.setStatus(list.stream().allMatch(MicroDeposit::getConfirmed)
            ? VerificationStatus.VERIFIED
            : VerificationStatus.PENDING_VERIFICATION);
        dto.setExpectedAmounts(list.stream()
            .map(MicroDeposit::getAmount)
            .collect(Collectors.toList()));
        dto.setEnteredAmounts(null);
        dto.setVerifiedAt(list.stream()
            .filter(MicroDeposit::getConfirmed)
            .map(MicroDeposit::getConfirmedAt)
            .findFirst()
            .orElse(null));
        return dto;
    }

    @Override
    @Transactional
    public void verifyMicroDeposits(UUID merchantId, UUID accountId, BankAccountVerifyRequest req) {
        List<MicroDeposit> list = microRepo.findByBankAccountId(accountId);
        if (req.getAmounts().size() != 2) {
            throw new IllegalArgumentException("Must supply two deposit amounts");
        }
        for (int i = 0; i < 2; i++) {
            if (!list.get(i).getAmount().equals(req.getAmounts().get(i))) {
                repo.findById(accountId)
                    .ifPresent(acc -> acc.setStatus(VerificationStatus.FAILED));
                throw new IllegalArgumentException("Deposit amounts do not match");
            }
        }
        list.forEach(md -> {
            md.setConfirmed(true);
            md.setConfirmedAt(Instant.now());
            microRepo.save(md);
        });
        BankAccount acc = repo.findById(accountId).get();
        acc.setStatus(VerificationStatus.VERIFIED);
        repo.save(acc);
    }
}

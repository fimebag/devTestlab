package com.fimepay.merchantapp.bankaccount;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/bank-accounts")
public class BankAccountController {

    private final BankAccountService service;

    @Autowired
    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<BankAccountDTO> list(@PathVariable UUID merchantId) {
        return service.list(merchantId);
    }

    @GetMapping("/{accountId}")
    public BankAccountDTO get(@PathVariable UUID merchantId,
                              @PathVariable UUID accountId) {
        return service.get(merchantId, accountId);
    }

    @PutMapping("/{accountId}")
    public BankAccountDTO update(@PathVariable UUID merchantId,
                                  @PathVariable UUID accountId,
                                  @RequestBody BankAccountUpdateRequest req) {
        return service.update(merchantId, accountId, req);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> delete(@PathVariable UUID merchantId,
                                       @PathVariable UUID accountId) {
        service.delete(merchantId, accountId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<BankAccountDTO> create(
            @PathVariable UUID merchantId,
            @Valid @RequestBody BankAccountLinkRequest req) {
        return ResponseEntity.ok(service.create(merchantId, req));
    }

    @PostMapping("/{accountId}/micro-deposits/resend")
    public ResponseEntity<Void> resend(
            @PathVariable UUID merchantId,
            @PathVariable UUID accountId) {
        service.resendMicroDeposits(merchantId, accountId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{accountId}/micro-deposits/status")
    public MicroDepositStatusDTO status(
            @PathVariable UUID merchantId,
            @PathVariable UUID accountId) {
        return service.getMicroDepositStatus(merchantId, accountId);
    }

    @PutMapping("/{accountId}/micro-deposits/verify")
    public ResponseEntity<Void> verify(
            @PathVariable UUID merchantId,
            @PathVariable UUID accountId,
            @Valid @RequestBody BankAccountVerifyRequest req) {
        service.verifyMicroDeposits(merchantId, accountId, req);
        return ResponseEntity.ok().build();
    }

}
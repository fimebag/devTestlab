package com.fimepay.merchantapp.transactions.controller;

import com.fimepay.merchantapp.transactions.dto.CreateFundingRequest;
import com.fimepay.merchantapp.transactions.dto.TransactionDTO;
import com.fimepay.merchantapp.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/wallet")
@Validated
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/fund")
    public ResponseEntity<TransactionDTO> fundWallet(
            @PathVariable UUID merchantId,
            @RequestBody @Validated CreateFundingRequest req
    ) {
        TransactionDTO dto = walletService.initiateFunding(merchantId, req);
        return ResponseEntity.ok(dto);
    }
}

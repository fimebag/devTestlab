// src/main/java/com/fimepay/merchantapp/controller/MerchantWalletController.java
package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.WalletDTO;
import com.fimepay.merchantapp.model.WalletType;
import com.fimepay.merchantapp.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant/{merchantId}/wallets")
public class MerchantWalletController {

    private final WalletService walletService;

    public MerchantWalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(
            @PathVariable String merchantId,
            @RequestParam("type") WalletType type,
            @RequestParam("currency") String currency) {
        return ResponseEntity.ok(walletService.createWallet(merchantId, type, currency));
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> listWallets(@PathVariable String merchantId) {
        return ResponseEntity.ok(walletService.listWallets(merchantId));
    }
}

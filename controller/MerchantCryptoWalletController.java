// src/main/java/com/fimepay/merchantapp/controller/MerchantCryptoWalletController.java
package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.*;
import com.fimepay.merchantapp.crypto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/crypto-wallets")
public class MerchantCryptoWalletController {

    private final CryptoWalletService service;

    public MerchantCryptoWalletController(CryptoWalletService service) {
        this.service = service;
    }

    /*@PostMapping
    public ResponseEntity<CryptoWalletDTOOld> create(
            @PathVariable String merchantId,
            @Valid @RequestBody CreateCryptoWalletRequestOld req) {
        return ResponseEntity.ok(service.create(merchantId, req));
    }

   @GetMapping
    public ResponseEntity<List<CryptoWalletDTOOld>> list(@PathVariable String merchantId) {
        return ResponseEntity.ok(service.list(merchantId));
    } 

    @PutMapping("/{walletId}")
    public ResponseEntity<CryptoWalletDTOOld> update(
            @PathVariable UUID walletId,
            @Valid @RequestBody CreateCryptoWalletRequestOld req) {
        return ResponseEntity.ok(service.update(walletId, req));
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> delete(@PathVariable UUID walletId) {
        service.delete(walletId);
        return ResponseEntity.noContent().build();
    } */
}

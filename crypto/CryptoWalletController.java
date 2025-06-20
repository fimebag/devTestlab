// src/main/java/com/fimepay/merchantapp/crypto/CryptoWalletController.java
package com.fimepay.merchantapp.crypto;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/crypto-wallets")
public class CryptoWalletController {

    private final CryptoWalletService service;

    @Autowired
    public CryptoWalletController(CryptoWalletService service) {
        this.service = service;
    }

    @GetMapping
    public List<CryptoWalletDTO> list(@PathVariable UUID merchantId) {
        return service.list(merchantId);
    }

    @GetMapping("/{walletId}")
    public CryptoWalletDTO get(@PathVariable UUID merchantId,
                               @PathVariable UUID walletId) {
        return service.get(merchantId, walletId);
    }

    @PostMapping
    public ResponseEntity<CryptoWalletDTO> create(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CryptoWalletLinkRequest req) {
        return ResponseEntity.ok(service.create(merchantId, req));
    }

    @PutMapping("/{walletId}")
    public CryptoWalletDTO update(
            @PathVariable UUID merchantId,
            @PathVariable UUID walletId,
            @Valid @RequestBody CryptoWalletUpdateRequest req) {
        return service.update(merchantId, walletId, req);
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID merchantId,
            @PathVariable UUID walletId) {
        service.delete(merchantId, walletId);
        return ResponseEntity.noContent().build();
    }
}

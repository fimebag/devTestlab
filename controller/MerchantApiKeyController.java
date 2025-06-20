package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.*;
import com.fimepay.merchantapp.service.ApiKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/api-keys")
public class MerchantApiKeyController {

    private final ApiKeyService service;

    public MerchantApiKeyController(ApiKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiKeyDTO> create(
            @PathVariable String merchantId,
            @Valid @RequestBody CreateApiKeyRequest req) {
        return ResponseEntity.ok(service.createApiKey(merchantId, req));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyDTO>> list(@PathVariable String merchantId) {
        return ResponseEntity.ok(service.listApiKeys(merchantId));
    }

    @DeleteMapping("/{keyId}")
    public ResponseEntity<Void> revoke(@PathVariable UUID keyId) {
        service.revokeApiKey(keyId);
        return ResponseEntity.noContent().build();
    }
}

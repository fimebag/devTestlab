package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.MerchantDTO;
import com.fimepay.merchantapp.dto.MerchantProfileUpdateRequest;
import com.fimepay.merchantapp.service.MerchantService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /**
     * GET /merchant/{merchantId}
     * Retrieves merchant profile details.
     */
    @GetMapping("/{merchantId}")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable String merchantId) {
        MerchantDTO merchantDTO = merchantService.getMerchantById(merchantId);
        return ResponseEntity.ok(merchantDTO);
    }
    
    /**
     * PUT /merchant/{merchantId}/profile
     * Update business description & primary contact info.
     */
    @PutMapping("/{merchantId}/profile")
    public ResponseEntity<MerchantDTO> updateProfile(
            @PathVariable String merchantId,
            @Valid @RequestBody MerchantProfileUpdateRequest req) {
        MerchantDTO updated = merchantService.updateProfile(merchantId, req);
        return ResponseEntity.ok(updated);
    }

    /**
     * POST /merchant/{merchantId}/profile/logo
     * Upload or replace company logo.
     */
    @PostMapping("/{merchantId}/profile/logo")
    public ResponseEntity<MerchantDTO> updateLogo(
            @PathVariable String merchantId,
            @RequestParam("file") MultipartFile file) {
        MerchantDTO updated = merchantService.updateLogo(merchantId, file);
        return ResponseEntity.ok(updated);
    }
    // Further dashboard endpoints like transaction history, API keys, payout monitoring, etc., can be added here.


}

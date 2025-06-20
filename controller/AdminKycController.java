package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.*;
import com.fimepay.merchantapp.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/kyc")
@PreAuthorize("hasRole('ADMIN')")    // restrict to admins
public class AdminKycController {

    private final MerchantService merchantService;
    public AdminKycController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /** List all pending KYC documents **/
    @GetMapping("/pending")
    public ResponseEntity<List<KycDocumentDTO>> pending() {
        List<KycDocumentDTO> docs = merchantService.listPendingKycDocuments();
        return ResponseEntity.ok(docs);
    }

    /** Approve or reject a specific document **/
    @PostMapping("/review/{docId}")
    public ResponseEntity<KycDocumentDTO> review(
            @PathVariable UUID docId,
            @RequestBody KycReviewRequest req) {
        KycDocumentDTO dto = merchantService.reviewKycDocument(docId, req);
        return ResponseEntity.ok(dto);
    }
}

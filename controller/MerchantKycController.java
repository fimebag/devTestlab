package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.KycDocumentDTO;
import com.fimepay.merchantapp.dto.MerchantDTO;
import com.fimepay.merchantapp.dto.MerchantKycRequest;
import com.fimepay.merchantapp.service.MerchantKycService;
import com.fimepay.merchantapp.service.MerchantService;
import com.fimepay.merchantapp.model.DocumentType;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/merchant/kyc")
public class MerchantKycController {

    private final MerchantKycService merchantKycService;
    private final MerchantService merchantService;


    public MerchantKycController(MerchantKycService merchantKycService, MerchantService merchantService) {
        this.merchantKycService = merchantKycService;
        this.merchantService = merchantService;
    }

    /**
     * POST /merchant/kyc/{merchantId}
     * Endpoint to submit KYC documents for a merchant.
     */
    @PostMapping("/{merchantId}")
    public ResponseEntity<MerchantDTO> submitKyc(@PathVariable String merchantId,
                                                 @Valid @RequestBody MerchantKycRequest request) {
        MerchantDTO merchantDTO = merchantKycService.submitKyc(merchantId, request);
        return ResponseEntity.ok(merchantDTO);
    }

    /** Upload or replace a specific KYC document **/
    @PostMapping("/{merchantId}/documents")
    public ResponseEntity<KycDocumentDTO> upload(
            @PathVariable String merchantId,
            @RequestParam("type") DocumentType type,
            @RequestParam("file") MultipartFile file) {
        KycDocumentDTO dto = merchantService.uploadKycDocument(merchantId, type, file);
        return ResponseEntity.ok(dto);
    }

    /** List all KYC documents & their statuses **/
    @GetMapping("/{merchantId}/documents")
    public ResponseEntity<List<KycDocumentDTO>> list(
            @PathVariable String merchantId) {
        List<KycDocumentDTO> docs = merchantService.listKycDocuments(merchantId);
        return ResponseEntity.ok(docs);
    }
}

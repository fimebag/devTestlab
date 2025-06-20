package com.fimepay.merchantapp.chargebacks.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fimepay.merchantapp.chargebacks.dto.ChargebackEventDTO;
import com.fimepay.merchantapp.chargebacks.dto.CreateChargebackRequestDTO;
import com.fimepay.merchantapp.chargebacks.service.ChargebackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/merchant/{merchantId}")
public class ChargebackController {

    private final ChargebackService cbSvc;

    public ChargebackController(ChargebackService cbSvc) {
        this.cbSvc = cbSvc;
    }

    @PostMapping("/transactions/{transactionId}/chargebacks")
    public ResponseEntity<ChargebackEventDTO> createChargeback(
        @PathVariable UUID merchantId,
        @PathVariable UUID transactionId,
        @Valid @RequestBody CreateChargebackRequestDTO req
    ) {
        return ResponseEntity.ok(
            cbSvc.createChargeback(merchantId, transactionId, req)
        );
    }

    @PostMapping("/chargebacks/{chargebackId}/dispute")
    public ResponseEntity<ChargebackEventDTO> disputeChargeback(
        @PathVariable UUID merchantId,
        @PathVariable UUID chargebackId
    ) {
        return ResponseEntity.ok(
            cbSvc.disputeChargeback(merchantId, chargebackId)
        );
    }

    @PostMapping("/chargebacks/{chargebackId}/resolve")
    public ResponseEntity<ChargebackEventDTO> resolveChargeback(
        @PathVariable UUID merchantId,
        @PathVariable UUID chargebackId
    ) {
        return ResponseEntity.ok(
            cbSvc.resolveChargeback(merchantId, chargebackId)
        );
    }

    @GetMapping("/chargebacks")
    public ResponseEntity<Page<ChargebackEventDTO>> listChargebacks(
        @PathVariable UUID merchantId,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="20") int size
    ) {
        Pageable pg = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(
            cbSvc.listChargebacks(merchantId, pg)
        );
    }

    @GetMapping("/chargebacks/{chargebackId}")
    public ResponseEntity<ChargebackEventDTO> getChargeback(
        @PathVariable UUID merchantId,
        @PathVariable UUID chargebackId
    ) {
        return ResponseEntity.ok(
            cbSvc.getChargeback(merchantId, chargebackId)
        );
    }
}

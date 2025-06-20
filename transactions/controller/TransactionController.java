package com.fimepay.merchantapp.transactions.controller;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.model.*;
import com.fimepay.merchantapp.transactions.service.TransactionService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/transactions")
public class TransactionController {

    private final TransactionService svc;

    public TransactionController(TransactionService svc) {
        this.svc = svc;
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDTO>> list(
            @PathVariable UUID merchantId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) TransactionStatus status,
            @RequestParam(required = false) PaymentChannel channel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<TransactionDTO> result = svc.listTransactions(
                merchantId, type, status, channel,
                PageRequest.of(page, size, Sort.by("createdAt").descending())
        );
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{txId}")
    public ResponseEntity<TransactionDTO> detail(@PathVariable UUID txId) {
        return ResponseEntity.ok(svc.getDetails(txId));
    }

    @PostMapping("/payout")
    public ResponseEntity<TransactionDTO> payout(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreatePayoutRequest req) {
        return ResponseEntity.ok(svc.initiatePayout(merchantId, req));
    }
}

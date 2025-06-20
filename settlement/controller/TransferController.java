package com.fimepay.merchantapp.settlement.controller;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.service.TransferService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/wallets")
public class TransferController {

    private final TransferService svc;

    public TransferController(TransferService svc) {
        this.svc = svc;
    }

    @PostMapping("/transfer")
    public ResponseEntity<InternalTransferDTO> create(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreateTransferRequest req) {
        return ResponseEntity.ok(svc.createTransfer(merchantId, req));
    }

    @GetMapping("/transfers")
    public ResponseEntity<Page<InternalTransferDTO>> list(
            @PathVariable UUID merchantId,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(svc.listTransfers(merchantId, pg));
    }

    @PutMapping("/transfer/{id}/approve")
    public ResponseEntity<InternalTransferDTO> approve(
            @PathVariable UUID merchantId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(svc.approveTransfer(merchantId, id));
    }
}

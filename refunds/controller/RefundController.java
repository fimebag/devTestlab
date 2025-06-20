package com.fimepay.merchantapp.refunds.controller;

import com.fimepay.merchantapp.refunds.dto.*;
import com.fimepay.merchantapp.refunds.service.RefundService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}")
public class RefundController {

    private final RefundService refundSvc;

    public RefundController(RefundService refundSvc) {
        this.refundSvc = refundSvc;
    }

    @PostMapping("/transactions/{transactionId}/refunds")
    public ResponseEntity<RefundRequestDTO> requestRefund(
        @PathVariable UUID merchantId,
        @PathVariable UUID transactionId,
        @Valid @RequestBody CreateRefundRequestDTO req
    ) {
        return ResponseEntity.ok(
            refundSvc.requestRefund(merchantId, transactionId, req)
        );
    }

    @PutMapping("/refunds/{refundId}/approve")
    public ResponseEntity<RefundRequestDTO> approveRefund(
        @PathVariable UUID merchantId,
        @PathVariable UUID refundId,
        @Valid @RequestBody ApproveRefundRequestDTO req
    ) {
        return ResponseEntity.ok(
            refundSvc.approveRefund(merchantId, refundId, req)
        );
    }

    @PutMapping("/refunds/{refundId}/reject")
    public ResponseEntity<RefundRequestDTO> rejectRefund(
        @PathVariable UUID merchantId,
        @PathVariable UUID refundId,
        @Valid @RequestBody RejectRefundRequestDTO req
    ) {
        return ResponseEntity.ok(
            refundSvc.rejectRefund(merchantId, refundId, req)
        );
    }

    @GetMapping("/refunds")
    public ResponseEntity<Page<RefundRequestDTO>> listRefunds(
        @PathVariable UUID merchantId,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="20") int size
    ) {
        Pageable pg = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(refundSvc.listRefunds(merchantId, pg));
    }

    @GetMapping("/refunds/{refundId}")
    public ResponseEntity<RefundRequestDTO> getRefund(
        @PathVariable UUID merchantId,
        @PathVariable UUID refundId
    ) {
        return ResponseEntity.ok(refundSvc.getRefund(merchantId, refundId));
    }
}

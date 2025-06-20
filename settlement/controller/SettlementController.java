package com.fimepay.merchantapp.settlement.controller;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.service.SettlementService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}")
public class SettlementController {

    private final SettlementService svc;

    public SettlementController(SettlementService svc) {
        this.svc = svc;
    }
    /*
    @PostMapping("/settlements")
    public ResponseEntity<SettlementDTO> schedule(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreateSettlementRequest req) {
        return ResponseEntity.ok(svc.scheduleSettlement(merchantId, req));
    }

    @GetMapping("/settlements")
    public ResponseEntity<Page<SettlementDTO>> list(
            @PathVariable UUID merchantId,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by("initiatedAt").descending());
        return ResponseEntity.ok(svc.listSettlements(merchantId, pg));
    }

    @PostMapping("/settlements/{id}/run")
    public ResponseEntity<SettlementDTO> run(
            @PathVariable UUID merchantId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(svc.runSettlementNow(merchantId, id));
    }
} */
}

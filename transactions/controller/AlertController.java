package com.fimepay.merchantapp.transactions.controller;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/alerts")
public class AlertController {

    private final AlertService svc;

    public AlertController(AlertService svc) {
        this.svc = svc;
    }

    @GetMapping
    public ResponseEntity<List<TransactionAlertDTO>> list(@PathVariable UUID merchantId) {
        return ResponseEntity.ok(svc.listAlerts(merchantId));
    }

    @PostMapping
    public ResponseEntity<TransactionAlertDTO> create(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreateAlertRequest req) {
        return ResponseEntity.ok(svc.createAlert(merchantId, req));
    }
}

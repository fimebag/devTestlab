package com.fimepay.merchantapp.controller;

import com.fimepay.merchantapp.dto.*;
import com.fimepay.merchantapp.service.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/webhooks")
public class MerchantWebhookController {

    private final WebhookService service;

    public MerchantWebhookController(WebhookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WebhookSubscriptionDTO> create(
            @PathVariable String merchantId,
            @Valid @RequestBody CreateWebhookSubscriptionRequest req) {
        return ResponseEntity.ok(service.createSubscription(merchantId, req));
    }

    @GetMapping
    public ResponseEntity<List<WebhookSubscriptionDTO>> list(@PathVariable String merchantId) {
        return ResponseEntity.ok(service.listSubscriptions(merchantId));
    }

    @PutMapping("/{subId}")
    public ResponseEntity<WebhookSubscriptionDTO> update(
            @PathVariable UUID subId,
            @Valid @RequestBody UpdateWebhookSubscriptionRequest req) {
        return ResponseEntity.ok(service.updateSubscription(subId, req));
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Void> delete(@PathVariable UUID subId) {
        service.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }
}

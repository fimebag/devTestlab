package com.fimepay.merchantapp.controller;


import com.fimepay.merchantapp.service.webhooks.WebhookHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final Map<String, WebhookHandler> handlerMap;

    public WebhookController(
        @Qualifier("flutterwaveHandler") WebhookHandler flutterwave,
        @Qualifier("paystackHandler") WebhookHandler paystack
    ) {
        handlerMap = new HashMap<>();
        handlerMap.put("flutterwave", flutterwave);
        handlerMap.put("paystack", paystack);
    }

    @PostMapping("/{gateway}")
    public ResponseEntity<String> receiveWebhook(
        @PathVariable String gateway,
        @RequestBody String payload,
        @RequestHeader(value = "verif-hash", required = false) String flutterwaveSignature,
        @RequestHeader(value = "x-paystack-signature", required = false) String paystackSignature
    ) {
        WebhookHandler handler = handlerMap.get(gateway.toLowerCase());
        if (handler == null) {
            return ResponseEntity.badRequest().body("Unsupported gateway");
        }

        String signature = gateway.equalsIgnoreCase("paystack") ? paystackSignature : flutterwaveSignature;
        handler.handleWebhook(payload, signature);
        return ResponseEntity.ok("Webhook processed");
    }
}

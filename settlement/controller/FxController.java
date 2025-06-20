package com.fimepay.merchantapp.settlement.controller;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.service.FxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/wallets")
public class FxController {

    private final FxService svc;

    public FxController(FxService svc) {
        this.svc = svc;
    }

    @PostMapping("/convert")
    public ResponseEntity<FxConversionDTO> convert(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreateFxRequest req) {
        return ResponseEntity.ok(svc.convert(merchantId, req));
    }
}

package com.fimepay.merchantapp.payout;

import com.fimepay.merchantapp.transactions.model.PaymentChannel;
import com.fimepay.merchantapp.payout.MPGSClient;
import com.fimepay.merchantapp.payout.CryptoClient;
import com.fimepay.merchantapp.payout.RemittanceClient;
import com.fimepay.merchantapp.payout.FxClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private final RestTemplate restTemplate;
    private final MPGSClient mpgs;
    private final CryptoClient crypto;
    private final RemittanceClient remittance;
    private final FxClient fx;

    public PaymentGatewayServiceImpl(MPGSClient mpgs,
                                     CryptoClient crypto,
                                     RemittanceClient remittance,
                                     FxClient fx,
                                     RestTemplate restTemplate) {
        this.mpgs       = mpgs;
        this.crypto     = crypto;
        this.remittance = remittance;
        this.fx         = fx;
        this.restTemplate = restTemplate;

    }

    @Value("${gateway.base-url}")
    private String gatewayBaseUrl;

    @Value("${gateway.api-key}")
    private String apiKey;


    private HttpHeaders authHeaders(String idempotencyKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.set("Idempotency-Key", idempotencyKey);
        return headers;
    }

   /**
     * Fire a payout with retry and circuit-breaker.
     */
    @Override
    @CircuitBreaker(name = "gatewayService", fallbackMethod = "payoutFallback")
    @Retry(name = "gatewayService")    
    public String sendPayout(String reference, UUID merchantId, BigDecimal amount, String currency) {
        String url = gatewayBaseUrl + "/payouts";
        Map<String, Object> body = new HashMap<>();
        body.put("reference", reference);
        body.put("merchant_id", merchantId);
        body.put("amount", amount);
        body.put("currency", currency);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, authHeaders(reference));
        ResponseEntity<Map> resp = restTemplate.exchange(url, HttpMethod.POST, req, Map.class);

        //if (resp.getStatusCode() == HttpStatus.CREATED || resp.getStatusCode() == HttpStatus.OK) {
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody()!=null) {
        // assume response JSON has an "id" we can return
            return resp.getBody().get("id").toString();
        }
        throw new RuntimeException("Payout failed: HTTP" + resp.getStatusCode());
    }
           // ④ Fallback method signature must match + Throwable
    private String payoutFallback(String reference, UUID merchantId, BigDecimal amount, String currency, Throwable ex) {
        // You can log, notify, or re‐queue here
        throw new RuntimeException("Payout failed after retries", ex);
    }

    @Override
    @CircuitBreaker(name = "gatewayService", fallbackMethod = "refundFallback")
    @Retry(name = "gatewayService")    
    public String sendRefund(String originalReference, String refundReference, BigDecimal amount) {
        String url = gatewayBaseUrl + "/refunds";
        Map<String, Object> body = new HashMap<>();
        body.put("original_reference", originalReference);
        body.put("reference", refundReference);
        body.put("amount", amount);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, authHeaders(originalReference));
        ResponseEntity<Map> resp = restTemplate.exchange(url, HttpMethod.POST, req, Map.class);

        //if (resp.getStatusCode() == HttpStatus.CREATED || resp.getStatusCode() == HttpStatus.OK) {
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody()!=null) {

            return resp.getBody().get("id").toString();
        }
        throw new RuntimeException("Refund failed: HTTP" + resp.getStatusCode());
    }

             // ④ Fallback method signature must match + Throwable
             private String refundFallback(String reference, UUID merchantId, BigDecimal amount, String currency, Throwable ex) {
                // You can log, notify, or re‐queue here
                throw new RuntimeException("refund failed after retries", ex);
            }

    @Override
    @CircuitBreaker(name = "gatewayService", fallbackMethod = "ChargebackFallback")
    @Retry(name = "gatewayService") 
    public String sendChargeback(String originalReference, String chargebackReference, BigDecimal amount) {
        String url = gatewayBaseUrl + "/chargebacks";
        Map<String, Object> body = new HashMap<>();
        body.put("original_reference", originalReference);
        body.put("reference", chargebackReference);
        body.put("amount", amount);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, authHeaders(originalReference));
        ResponseEntity<Map> resp = restTemplate.exchange(url, HttpMethod.POST, req, Map.class);

        //if (resp.getStatusCode() == HttpStatus.CREATED || resp.getStatusCode() == HttpStatus.OK) {
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody()!=null) {
                return resp.getBody().get("id").toString();
        }
        throw new RuntimeException("Chargeback failed: HTTP" + resp.getStatusCode());
    }

    private String ChargebackFallback(String reference, UUID merchantId, BigDecimal amount, String currency, Throwable ex) {
        // You can log, notify, or re‐queue here
        throw new RuntimeException("chargeback failed after retries", ex);
    }


@Override
    public String charge(String reference,
                         UUID merchantId,
                         BigDecimal amount,
                         String currency,
                         PaymentChannel channel) {
        switch (channel) {
            case MPGS:
                return mpgs.sendCharge(reference, merchantId, amount, currency);
            case CRYPTO:
                return crypto.sendCharge(reference, merchantId, amount, currency);
            case REMITTANCE:
                return remittance.sendTransfer(reference, merchantId, amount, currency);
            case BANK_FX:
                return fx.sendDeposit(reference, merchantId, amount, currency);
            default:
                throw new IllegalArgumentException("Unsupported channel: " + channel);
        }
    }

    

}


  
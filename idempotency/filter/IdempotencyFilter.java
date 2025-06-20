package com.fimepay.merchantapp.idempotency.filter;

import com.fimepay.merchantapp.idempotency.model.IdempotencyKey;
import com.fimepay.merchantapp.idempotency.repository.IdempotencyKeyRepository;
import com.fimepay.merchantapp.service.MerchantService;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

@Component
public class IdempotencyFilter extends OncePerRequestFilter {

  private final IdempotencyKeyRepository repo;
  private final MerchantService          merchantService;
  private final Duration                 ttl;

  public IdempotencyFilter(IdempotencyKeyRepository repo,
                           MerchantService merchantService,
                           @Value("${idempotency.ttl}") Duration ttl) {
    this.repo            = repo;
    this.merchantService = merchantService;
    this.ttl             = ttl;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest  request,
                                  HttpServletResponse response,
                                  FilterChain         chain)
      throws ServletException, IOException {

    String key       = request.getHeader("Idempotency-Key");
    boolean isPayout = "POST".equalsIgnoreCase(request.getMethod())
                    && request.getRequestURI().contains("/payout");

    // Wrap immediately so we can cache bodies
    ContentCachingRequestWrapper  wrappedReq  = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper wrappedResp = new ContentCachingResponseWrapper(response);

    // 1) Bypass if not a payout or no key
    if (key == null || !isPayout) {
      chain.doFilter(wrappedReq, wrappedResp);
      wrappedResp.copyBodyToResponse();
      return;
    }

    // 2) Must be authenticated
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      chain.doFilter(wrappedReq, wrappedResp);
      wrappedResp.copyBodyToResponse();
      return;
    }
    UUID merchantId = merchantService.getMerchantByEmail(auth.getName()).getId();

    String method = wrappedReq.getMethod();
    String path   = wrappedReq.getRequestURI();

    // 3) *** Check for existing record BEFORE chain.doFilter ***
    Optional<IdempotencyKey> existing = repo
      .findByMerchantIdAndKeyAndMethodAndPath(merchantId, key, method, path);
    if (existing.isPresent()) {
      IdempotencyKey rec = existing.get();
      response.setStatus(rec.getResponseStatus());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write(rec.getResponseBody());
      response.getWriter().flush();
      return;
    }

    // 4) First time: invoke the controller
    chain.doFilter(wrappedReq, wrappedResp);

    // 5) Now that both request & response are cached, compute hash + response
    String body        = new String(wrappedReq.getContentAsByteArray(), UTF_8);
    String requestHash = sha256(body);
    int    status      = wrappedResp.getStatus();
    String respBody    = new String(wrappedResp.getContentAsByteArray(), UTF_8);

    // 6) Persist the idempotency record for future retries
    IdempotencyKey rec = new IdempotencyKey();
    rec.setMerchantId(   merchantId);
    rec.setKey(          key);
    rec.setMethod(       method);
    rec.setPath(         path);
    rec.setRequestHash(  requestHash);
    rec.setResponseStatus(status);
    rec.setResponseBody(  respBody);
    rec.setCreatedAt(    LocalDateTime.now());
    rec.setExpiresAt(    LocalDateTime.now().plus(ttl));
    repo.save(rec);

    // 7) Finally, copy the buffered response back to the client
    wrappedResp.copyBodyToResponse();
  }



    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2    ] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private String sha256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash request body", e);
        }
    }
}


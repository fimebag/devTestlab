
package com.fimepay.merchantapp.auth.controller;

import com.fimepay.merchantapp.auth.dto.*;
import com.fimepay.merchantapp.auth.model.RefreshToken;
import com.fimepay.merchantapp.auth.service.RefreshTokenService;
import com.fimepay.merchantapp.dto.*;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.security.JwtUserPrincipal;
import com.fimepay.merchantapp.security.JwtUtil;
import com.fimepay.merchantapp.service.MerchantService;
import com.fimepay.merchantapp.service.VerificationService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/merchant")
public class AuthController {

    private final MerchantService merchantSvc;
    private final VerificationService verifSvc;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshSvc;

    public AuthController(MerchantService merchantSvc,
                          VerificationService verifSvc,
                          PasswordEncoder encoder,
                          JwtUtil jwtUtil,
                          RefreshTokenService refreshSvc) {
        this.merchantSvc = merchantSvc;
        this.verifSvc    = verifSvc;
        this.encoder     = encoder;
        this.jwtUtil     = jwtUtil;
        this.refreshSvc  = refreshSvc;
    }

    //
    // 1) Registration & Verification flows
    /**
     * 

    @PostMapping("/register")
    public ResponseEntity<MerchantDTO> register(
            @Valid @RequestBody MerchantRegistrationRequest req) {

        // 1. hash & save merchant
        req.setPassword(encoder.encode(req.getPassword()));
        MerchantDTO dto = merchantSvc.registerMerchant(req);

        // 2. issue verify tokens
        verifSvc.generateEmailToken(dto.getEmail());
        verifSvc.generatePhoneOtp(dto.getEmail());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(
            @Valid @RequestBody EmailVerificationRequest req) {
        verifSvc.verifyEmail(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-phone")
    public ResponseEntity<Void> verifyPhone(
            @Valid @RequestBody PhoneVerificationRequest req) {
        verifSvc.verifyPhone(req);
        return ResponseEntity.ok().build();
    }

    //
    // 2) Login → returns both tokens
    //

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody MerchantLoginRequest req) {

        Merchant m = merchantSvc.getMerchantByEmail(req.getEmail());
        if (!encoder.matches(req.getPassword(), m.getPasswordHash())) {
            return ResponseEntity.status(401).build();
        }
        if (!m.isEmailVerified() || !m.isPhoneVerified()) {
            return ResponseEntity.status(403).build();
        }

        // issue JWT access token
        String accessToken  = jwtUtil.generateToken(m.getEmail());
        // create + persist refresh token
        String refreshToken = refreshSvc.createRefreshToken(m.getId()).getToken();

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    //
    // 3) Refresh access token
    //



    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @Valid @RequestBody TokenRefreshRequest request) {

        // 1. validate the presented refresh token
        RefreshToken rt = refreshSvc.verifyRefreshToken(request.getRefreshToken());

        // 2. get the merchant’s email directly from the token
        String merchantEmail = rt.getMerchant().getEmail();

        // 3. issue a new access token (you can keep re-using the same refresh token)
        String newAccess = jwtUtil.generateToken(merchantEmail);

        return ResponseEntity.ok(new AuthResponse(newAccess, request.getRefreshToken()));
    }

    // 4. Logout 
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> logout(Authentication auth) {
        Merchant m = merchantSvc.getMerchantByEmail(auth.getName());
        refreshSvc.revokeRefreshTokens(m.getId());
        return ResponseEntity.noContent().build();
    }
     */
    
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication authentication) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401)
                .body(Map.of("error", "Not authenticated"));
        }
        
        if (authentication.getPrincipal() instanceof JwtUserPrincipal) {
            JwtUserPrincipal principal = (JwtUserPrincipal) authentication.getPrincipal();
            return ResponseEntity.ok(Map.of(
                "username", principal.getUsername(),
                "userId", principal.getUserId(),
                "authenticated", true
            ));
        }
        
        return ResponseEntity.ok(Map.of(
            "username", authentication.getName(),
            "authenticated", true
        ));
    }

    /**
     * Since merchantapp doesn't manage tokens, "logout" just clears client-side token
     * You might want to notify customerapp about logout for centralized token revocation
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(Authentication authentication) {
        
        // Option 1: Just return success (client should clear token)
        return ResponseEntity.ok(Map.of(
            "message", "Logged out successfully. Please clear your token.",
            "action", "clear_token"
        ));
        
        // Option 2: If you want to notify customerapp about logout
        // You could make an API call to customerapp to revoke the token
        // customerAppService.revokeToken(extractTokenFromAuth(authentication));
    }

    // ─── OPTIONAL: Health Check for Auth System ─────────────────────────────────
    /**
     * Health check endpoint to verify JWT validation is working
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> authHealth() {
        return ResponseEntity.ok(Map.of(
            "status", "healthy",
            "service", "merchantapp-auth",
            "jwt_validation", "enabled",
            "timestamp", java.time.Instant.now().toString()
        ));
    }
}
